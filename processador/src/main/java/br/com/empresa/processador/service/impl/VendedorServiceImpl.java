package br.com.empresa.processador.service.impl;

import br.com.empresa.processador.model.Vendedor;
import br.com.empresa.processador.repository.VendedorRepository;
import br.com.empresa.processador.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendedorServiceImpl implements VendedorService {

    @Autowired
    private final VendedorRepository repository;

    public VendedorServiceImpl(VendedorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void salvar(Vendedor vendedor) {
        repository.save(vendedor);
    }
}
