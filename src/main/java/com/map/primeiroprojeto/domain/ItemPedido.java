package com.map.primeiroprojeto.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@JsonIgnore //MAP	
	@EmbeddedId
	private ItemPedidoPK id= new ItemPedidoPK();
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	
	

	//CONSTRUCTORS-------------------------------------------
	public ItemPedido() {
		
	}
	

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);    //poderia ser this.id.setPedido(pedido);
		id.setProduto(produto);  //poderia ser this.id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	//------------------------------------------------------

	






	//GETTERS e SETTERS ------------------------------------	
	
	public ItemPedidoPK getId() {
		return id;
	}


	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	@JsonIgnore //MAP
	public Pedido getPedido() {
		return id.getPedido();		 
	}
	
	//@JsonIgnore //MAP
	public Produto getProduto() {
		return id.getProduto();
		
	}
	
	
	
	
	
	public Double getDesconto() {
		return desconto;
		
	}


	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Double getPreco() {
		return preco;
	}


	public void setPreco(Double preco) {
		this.preco = preco;
	}




	
	
	
	
	

	
	
}
