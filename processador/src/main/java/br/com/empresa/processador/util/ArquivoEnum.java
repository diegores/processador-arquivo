package br.com.empresa.processador.util;

import java.util.stream.Stream;

import lombok.Getter;

public enum ArquivoEnum {

    VENDEDOR("001"),
    CLIENTE("002"),
    VENDA("003");

    @Getter
    private String code;

    ArquivoEnum(String code) {
        this.code = code;
    }

    /**
     * Obtem o {@link ArquivoEnum} através do código informado.
     *
     * @param code Código de {@link ArquivoEnum}
     * @return {@link ArquivoEnum} correspondente
     */
    public static ArquivoEnum getValue(String code) {
        return Stream.of(values())
                .filter(e -> e.code.equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }
}
