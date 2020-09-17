package br.com.empresa.processador.util;

import java.util.Optional;

import br.com.empresa.processador.model.Venda;

public class MontarRelatorio {

    StringBuilder builder = new StringBuilder();

    public static MontarRelatorio getInstance() {
        return new MontarRelatorio();
    }

    /**
     * Monta o cabeçalho do relatório com o nome do arquivo.
     *
     * @param fileName nome do arquivo de entrada
     * @return {@link MontarRelatorio}
     */
    public MontarRelatorio withHeader(String fileName) {

        builder.append("### Relatório do arquivo");

        if (fileName != null && !fileName.trim().isEmpty()) {
            builder.append(fileName);
        }
        builder.append(" ###").append(System.getProperty("line.separator"));

        return this;
    }

    /**
     * Monta a quantidade de clientes no relatório.
     *
     * @param quantidadeClientes Quantidade de clientes
     * @return {@link MontarRelatorio}
     */
    public MontarRelatorio withQuantidadeClientes(Integer quantidadeClientes) {

        if (quantidadeClientes > 0) {
            builder.append("Quantidade de clientes no arquivo de entrada: ").append(quantidadeClientes)
                    .append(System.getProperty("line.separator"));
        }
        return this;
    }

    /**
     * Monta a quantidade de vendedores no relatório.
     *
     * @param quantidadeVendedores Quantidade de vendedores
     * @return {@link MontarRelatorio}
     */
    public MontarRelatorio withQuantidadeVendedores(Integer quantidadeVendedores) {

        if (quantidadeVendedores > 0) {
            builder.append("Quantidade de vendedores no arquivo de entrada: ").append(quantidadeVendedores)
                    .append(System.getProperty("line.separator"));
        }
        return this;
    }

    /**
     * Monta a venda mais cara no relatório.
     *
     * @param venda Venda mais cara.
     * @return {@link MontarRelatorio}
     */
    public MontarRelatorio withVendaMaisCara(Optional<Venda> venda) {

        venda.ifPresent(value -> builder.append("ID da venda mais cara: ").append(value.getIdVenda())
                .append(System.getProperty("line.separator")));
        return this;

    }

    /**
     * Monta o pior vendedor no relatório.
     *
     * @param venda Venda com o pior vendedor
     * @return {@link MontarRelatorio}
     */
    public MontarRelatorio withPiorVendedor(Optional<Venda> venda) {
        venda.ifPresent(v -> builder.append("O pior vendedor: ").append(v.getNomeVendedor()));

        return this;
    }

    public String build() {
        return builder.toString();
    }
}
