package br.com.empresa.processador.service;

import br.com.empresa.processador.model.Venda;

import java.util.Optional;

public interface VendaService {

    Venda salvar(Venda venda);

    Optional<Venda> getVendaMaisCara();

    Optional<Venda> getPiorVendedor();

    void limparVenda();
}
