package br.com.empresa.processador.repository;

import br.com.empresa.processador.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
}
