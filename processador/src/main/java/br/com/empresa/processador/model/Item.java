package br.com.empresa.processador.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "item")
@Data
@Setter
@Getter
public class Item {

    @Id
    private Long id;
    private Integer quantidade;
    private BigDecimal preco;

    /**
     * @param id
     * @param quantidade
     * @param preco
     */
    public Item(Long id, Integer quantidade, BigDecimal preco) {
        super();
        this.id = id;
        this.quantidade = quantidade;
        this.preco = preco;
    }
}
