package com.map.primeiroprojeto.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.map.primeiroprojeto.domain.enums.EstadoPagamento;

@Entity	
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	
	public PagamentoComBoleto() {		
	}


	//opcao 01 de CONSTRUCTOR baseado na superClasse
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento=dataPagamento;
		this.dataVencimento=dataVencimento;

	}

	//GETTERS e SETTERS--------------------------------
	public Date getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


	public Date getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}


	//opcao 02 de Constructor com parametros na mao
	/*
	public PagamentoComBoleto(Date dataVencimento, Date dataPagamento) {
		super();
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}
	*/
	
	
	
	
}
