package br.com.empresa.processador.service.impl;

import br.com.empresa.processador.service.ClienteService;
import br.com.empresa.processador.service.InputFileService;
import br.com.empresa.processador.service.VendaService;
import br.com.empresa.processador.service.VendedorService;
import br.com.empresa.processador.util.ArquivoEnum;
import br.com.empresa.processador.util.ArquivoUtil;
import br.com.empresa.processador.util.UtilConversor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;


@Service
public class InputFileServiceImpl implements InputFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InputFileServiceImpl.class);

    private final ClienteService clienteService;
    private final VendedorService vendedorService;
    private final VendaService vendaService;

    public InputFileServiceImpl(ClienteService clienteService, VendedorService vendedorService, VendaService vendaService) {
        this.clienteService = clienteService;
        this.vendedorService = vendedorService;
        this.vendaService = vendaService;
    }

    @Override
    public void processar(Stream<String> linhaArquivo) throws IllegalArgumentException {
        System.out.println(linhaArquivo);

        linhaArquivo.forEach(linha ->{
            if(ArquivoUtil.isValidInputLine(linha)){

                String[] caracter = linha.split("Ã§");
                ArquivoEnum tipoArquivo = ArquivoEnum.getValue(caracter[0].substring(0,3));

                try {
                    switch (tipoArquivo){
                        case CLIENTE:
                            clienteService.salvar(UtilConversor.convertToCliente(caracter));
                            break;
                        case VENDEDOR:
                            vendedorService.salvar(UtilConversor.convertToVendedor(caracter));
                            break;
                        case VENDA:
                            vendaService.salvar(UtilConversor.convertToVenda(caracter));
                            break;
                        default:
                    }

                }catch (NullPointerException e){
                    throw new IllegalArgumentException("Valor inválido");
                }
            }else{
                LOGGER.error("erro linha");
            }
        });


    }
}
