package br.com.empresa.processador.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "venda")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"itens", "totalVenda"})
@ToString(exclude = {"itens", "totalVenda"})
public class Venda {

    @Id
    private Long idVenda;
    private List<Item> itens;
    private String nomeVendedor;
    @Transient
    private BigDecimal totalVenda;

    public Venda(Long idVenda, List<Item> itens, String nomeVendedor) {
        this.idVenda = idVenda;
        this.itens = itens;
        this.nomeVendedor = nomeVendedor;
    }

}
