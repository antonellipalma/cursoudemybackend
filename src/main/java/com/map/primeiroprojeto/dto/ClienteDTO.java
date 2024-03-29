package com.map.primeiroprojeto.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.map.primeiroprojeto.domain.Cliente;

public class ClienteDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	
	@NotEmpty(message="Preenchimento Obrigatorio")
	@Length(min=5, message="O tamanho deve ser entre")
	private String nome;
	
	@NotEmpty(message="Preenchimento Obrigatorio")
	@Email
	private String email;
	//String cpfOuCnpj;
	//TipoCliente tipo;
	
	
	public ClienteDTO() {}

	
	public ClienteDTO(Cliente obj) {
		id=obj.getId();
		nome=obj.getNome();
		email=obj.getEmail();
		
	}
	
	
	
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
