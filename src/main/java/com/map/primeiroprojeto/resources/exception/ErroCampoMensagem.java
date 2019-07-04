package com.map.primeiroprojeto.resources.exception;

import java.io.Serializable;

public class ErroCampoMensagem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nomeCampo;
	private String  mensagemErro;

	public ErroCampoMensagem() {
		
	}

	public ErroCampoMensagem(String nomeCampo, String mensagemErro) {
		super();
		this.nomeCampo = nomeCampo;
		this.mensagemErro = mensagemErro;
	}

	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	
	
}
