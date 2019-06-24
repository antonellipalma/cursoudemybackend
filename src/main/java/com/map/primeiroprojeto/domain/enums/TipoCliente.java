package com.map.primeiroprojeto.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1,"Pessoa Física"),
	PESSOAJURIDICA(2,"Pessoa Juridica");
	
	private int cod; //tem q ser int (tipo primitivo) e nao Integer....int sempre tem valor 0...n ->Integer é a classe Wrapper que pode aceitar null
	private String descricao;
	
	
	
	
	
	
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	
	//GETTER - ENUMS tem somente metodos GET....pois a descricao ja esta pre-definida
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	
	
	
	public static TipoCliente toEnum (Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for(TipoCliente x: TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;				
			}
		}
		
	
		throw new IllegalArgumentException("Id invalido: "+ cod);
		
	}
	
	
	
}
