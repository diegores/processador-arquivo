package br.com.empresa.processador;

public class teste {

	public static void main(String[] args) {
		String [] vendedor = new String[200];
		String [] teste = new String [200];
		vendedor[0] = "001ç1234567891234çPedroç50000";
		
		teste = vendedor[0].split("ç");
		
		
		
		System.out.println(teste);

	}
}
