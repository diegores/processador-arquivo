package br.com.empresa.processador.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author tiago.costa
 */
@Data
@Document(collection = "vendedor")
@Getter
@Setter
public class Vendedor implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    private String cpf;
    private String nome;
    private BigDecimal salario;


    public Vendedor() {

    }

    public Vendedor(String cpf, String nome, BigDecimal salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Vendedor [cpf=" + cpf + ", nome=" + nome + ", salario=" + salario + "]";
    }


}
