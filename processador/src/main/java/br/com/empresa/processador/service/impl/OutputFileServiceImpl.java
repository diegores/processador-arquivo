package br.com.empresa.processador.service.impl;

import br.com.empresa.processador.model.Venda;
import br.com.empresa.processador.repository.ClienteRepository;
import br.com.empresa.processador.repository.VendedorRepository;
import br.com.empresa.processador.service.OutputFileService;
import br.com.empresa.processador.service.VendaService;
import br.com.empresa.processador.util.ArquivoUtil;
import br.com.empresa.processador.util.MontarRelatorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class OutputFileServiceImpl implements OutputFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutputFileServiceImpl.class);

    @Value("${file.out}")
    private String fileOut;

    private final VendedorRepository vendedorRepository;
    private final ClienteRepository clienteRepository;
    private final VendaService vendaService;

    public OutputFileServiceImpl(VendedorRepository vendedorRepository, ClienteRepository clienteRepository,
                                 VendaService vendaService) {

        this.vendedorRepository = vendedorRepository;
        this.clienteRepository = clienteRepository;
        this.vendaService = vendaService;
    }

    @Override
    public void gerarRelatorio(String nomeArquivo) {

        LOGGER.info("Buscando informações para geração do relatório");

        Integer qtdClientes = clienteRepository.findAll().size();
        Integer qtdVendedores = vendedorRepository.findAll().size();
        Optional<Venda> vendaMaisCara = vendaService.getVendaMaisCara();
        Optional<Venda> vendaComPiorVendedor = vendaService.getPiorVendedor();

        if (isVerificarDados(qtdClientes, qtdVendedores, vendaMaisCara, vendaComPiorVendedor)) {
            LOGGER.info("Dados não encontrados");
        } else {

            LOGGER.info("Iniciando a geração do relatório");

            String report = MontarRelatorio.getInstance().withHeader(nomeArquivo).withQuantidadeClientes(qtdClientes)
                    .withQuantidadeVendedores(qtdVendedores).withVendaMaisCara(vendaMaisCara)
                    .withPiorVendedor(vendaComPiorVendedor).build();

            LOGGER.info("Relatório concluido com sucesso! Salvando na saida.");

            escreverArquivo(nomeArquivo, report);
        }

    }

    @Override
    public void limparDados() {
        // TODO Auto-generated method stub

    }

    /**
     * @param qtdClientes
     * @param qtdVendedores
     * @param vendaMaisCara
     * @param vendaComPiorVendedor
     * @return
     */
    private boolean isVerificarDados(Integer qtdClientes, Integer qtdVendedores, Optional<Venda> vendaMaisCara,
                                     Optional<Venda> vendaComPiorVendedor) {
        return qtdClientes == 0 && qtdVendedores == 0 && !vendaMaisCara.isPresent()
                && !vendaComPiorVendedor.isPresent();
    }

    private void escreverArquivo(String nomeArquivo, String report) {
        try {

            ArquivoUtil.write(fileOut.concat("/"), nomeArquivo, report);
            LOGGER.info("Arquivo gerado com sucesso!");

            limparDados();

        } catch (IOException e) {
            LOGGER.error("Ocorreu um erro durante a geraÃ§Ã£o do relatÃ³rio: ");
            LOGGER.error(e.getMessage());
        }
    }

}
