package com.map.primeiroprojeto.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends ErroPadraoAplicacao {
	private static final long serialVersionUID = 1L;
	
	List<ErroCampoMensagem> listaErros= new ArrayList<>();
	
	
	
	
	
	
	public ErroValidacao(Integer status, String msg, Long timeStamp ) {
		super(status, msg, timeStamp);		
	}

	public List<ErroCampoMensagem> getlistaErros() {
		return listaErros;
	}

	public void setLista(List<ErroCampoMensagem> lista) {
		this.listaErros = lista;
	}
	
	
	public void addErroLista(String nomeDoCampo, String mensagemDeErro) {
		this.listaErros.add(new ErroCampoMensagem(nomeDoCampo, mensagemDeErro));
	}

}
