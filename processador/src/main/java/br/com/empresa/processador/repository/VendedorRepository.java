package br.com.empresa.processador.repository;

import br.com.empresa.processador.model.Vendedor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendedorRepository extends MongoRepository<Vendedor, String> {
}
