package br.com.empresa.processador.service.impl;

import br.com.empresa.processador.model.Item;
import br.com.empresa.processador.model.Venda;
import br.com.empresa.processador.repository.VendaRepository;
import br.com.empresa.processador.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class VendaServiceImpl implements VendaService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private final VendaRepository repository;

    public VendaServiceImpl(VendaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Venda salvar(Venda venda) {
        return mongoTemplate.save(venda);
    }

    @Override
    public Optional<Venda> getVendaMaisCara() {

        List<Venda> listVenda = repository.findAll();

        listVenda.forEach(venda -> venda.setTotalVenda(venda.getItens()
                .stream()
                .map(Item::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add)));
        return listVenda.stream()
                .max(Comparator.comparing(Venda::getTotalVenda));

    }

    @Override
    public Optional<Venda> getPiorVendedor() {
        List<Venda> listVenda = repository.findAll();
        listVenda.forEach(venda -> venda.setTotalVenda(venda.getItens()
                .stream()
                .map(Item::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add)));
        return listVenda.stream()
                .min(Comparator.comparing(Venda::getTotalVenda));
    }

    @Override
    public void limparVenda() {
        repository.deleteAll();
    }
}
