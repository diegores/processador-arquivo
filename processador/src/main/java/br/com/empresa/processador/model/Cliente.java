package br.com.empresa.processador.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cliente")
@Data
@Setter
@Getter
public class Cliente {

    @Id
    private String cnpj;
    private String nome;
    private String areaNegocio;


    /**
     * @param cnpj
     * @param nome
     * @param areaNegocio
     */
    public Cliente(String cnpj, String nome, String areaNegocio) {
        super();
        this.cnpj = cnpj;
        this.nome = nome;
        this.areaNegocio = areaNegocio;
    }
}
