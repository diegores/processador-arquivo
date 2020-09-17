package br.com.empresa.processador.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.empresa.processador.model.Cliente;
import br.com.empresa.processador.model.Venda;
import br.com.empresa.processador.model.Vendedor;
import br.com.empresa.processador.model.Cliente;
import br.com.empresa.processador.model.Item;
import br.com.empresa.processador.model.Venda;
import br.com.empresa.processador.model.Vendedor;


public class UtilConversor {

    
    public static Vendedor convertToVendedor(String[] fields) {
    	String [] converterVendedor = new String [] {};
    	converterVendedor = fields[0].split("ç");
    	
        return new Vendedor(converterVendedor[1], converterVendedor[2], new BigDecimal(converterVendedor[3]));
    }

    
    public static Cliente convertToCliente(String[] fields) {
        return new Cliente(fields[1], fields[2], fields[3]);
    }


    
    public static Venda convertToVenda(String[] fields) throws IllegalArgumentException {

        String itemStr = fields[2];

        String[] itens = itemStr.split(",");

        List<Item> itemList = convertToItens(itens);

        return new Venda(Long.valueOf(fields[1]), itemList, fields[3]);
    }


    
    public static List<Item> convertToItens(String[] itemArray) throws IllegalArgumentException {

        List<Item> itens = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String[] itemFields = itemArray[i].split("-");

            if (itemFields.length != 3) {
                throw new IllegalArgumentException("Item inválido! Item: " + itemArray[i]);
            }
            itens.add(new Item(Long.valueOf(itemFields[0].replaceAll("[\\[\\]]", "")),
                    Integer.valueOf(itemFields[1]),
                    new BigDecimal(itemFields[2].replaceAll("[\\[\\]]", ""))));
        }

        return itens;
    }
}
