package br.com.empresa.processador.service.impl;

import br.com.empresa.processador.model.Cliente;
import br.com.empresa.processador.repository.ClienteRepository;
import br.com.empresa.processador.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private final ClienteRepository repository;

    /**
     *
     * @param repository
     */
    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void salvar(Cliente cliente) {
        repository.save(cliente);
    }
}
