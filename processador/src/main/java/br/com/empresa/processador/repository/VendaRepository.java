package br.com.empresa.processador.repository;

import br.com.empresa.processador.model.Venda;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendaRepository extends MongoRepository<Venda, Long> {
}
