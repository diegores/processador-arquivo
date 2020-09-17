package br.com.empresa.processador.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.empresa.processador.service.InputFileService;
import br.com.empresa.processador.service.OutputFileService;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * Serviço observador dos diretórios de entrada e saída.
 *
 */
@Component
public class WatcherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WatcherService.class);

    private final InputFileService inputFileService;
    private final OutputFileService outputFileService;

    public WatcherService(InputFileService inputFileService, OutputFileService outputFileService) {
        this.inputFileService = inputFileService;
        this.outputFileService = outputFileService;
    }

    @Value("${file.in}")
    private String fileIn;
    @Value("${file.out}")
    private String fileOut;

    public void watch() throws Exception {

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {

            initialConfig();
            registerEvent(watchService);
            WatchKey watchKey;

            do {
                watchKey = watchService.take();

                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    Path eventPath = (Path) event.context();

                    if (eventPath.toString().endsWith(".dat")) {

                        LOGGER.info("Iniciando a leitura e processamento do arquivo: " + eventPath);

                        try {

                            Stream<String> lines = ArquivoUtil.read(fileIn, eventPath.toString());
                            inputFileService.processar(lines);
                            LOGGER.info("Finalizada a leitura e processamento do arquivo: " + eventPath);

                        } catch (IOException e) {

                            LOGGER.error("Ocorreu um erro ao tentar processar o arquivo. " +
                                    "O arquivo não pode ser lido, verifique seu conteúdo e charset!");
                            LOGGER.error("Erro: " + e.getMessage());

                            outputFileService.limparDados();

                        } catch (IllegalArgumentException e) {

                            LOGGER.error("Ocorreu um erro ao tentar processar o arquivo. " +
                                    "Existem linhas fora do padrão de entrada!");
                            LOGGER.error("Erro: " + e.getMessage());

                            outputFileService.limparDados();
                        } catch (Exception e) {

                            LOGGER.error("Ocorreu um erro desconhecido ao tentar processar o arquivo. " +
                                    "Por favor verifique o mesmo e tente novamente mais tarde.");
                            LOGGER.error("Erro: " + e.getMessage());

                            outputFileService.limparDados();
                        }

                        outputFileService.gerarRelatorio(eventPath.toString().replace(".dat", ".done.dat"));

                    } else {
                        LOGGER.info("Extensão de arquivo não válida! O arquivo deve ser no formato '.dat'");
                    }
                }
            } while (watchKey.reset());

        } catch (IOException e) {
            LOGGER.error("Ocorreu um na inicialização do programa: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    private void initialConfig() {
        ArquivoUtil.createDir(fileIn);
        ArquivoUtil.createDir(fileOut);
    }

    private void registerEvent(WatchService watchService) throws IOException {
        Path path = Paths.get(fileIn);
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
    }
}
