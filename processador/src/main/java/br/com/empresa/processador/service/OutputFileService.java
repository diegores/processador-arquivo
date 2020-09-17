package br.com.empresa.processador.service;

public interface OutputFileService {

    void gerarRelatorio(String nomeArquivo);

    void limparDados();
}