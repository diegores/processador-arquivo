package br.com.empresa.processador.service;

import java.util.stream.Stream;

public interface InputFileService {

    void processar(Stream<String> linhaArquivo) throws IllegalArgumentException;
}
