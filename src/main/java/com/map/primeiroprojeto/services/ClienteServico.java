package com.map.primeiroprojeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.map.primeiroprojeto.domain.Cliente;
import com.map.primeiroprojeto.domain.Cliente;
import com.map.primeiroprojeto.dto.ClienteDTO;
import com.map.primeiroprojeto.repositories.ClienteRepository;
import com.map.primeiroprojeto.services.exception.DataIntegrityException;
import com.map.primeiroprojeto.services.exception.ObjectNotFoundException;

@Service
public class ClienteServico {
	
	
	@Autowired
	ClienteRepository repo;
	
	//BUSCAR --------------------------------------------------
	public Cliente buscar(Integer id) {		
		Optional<Cliente> cliente= repo.findById(id);
		return cliente.orElseThrow( ()-> new ObjectNotFoundException("CLIENTE NAO ENCONTRADO- ERRO: "+ "TIPO: "+ Cliente.class.getName())    );
		
	}
	
	
	
	//*************************

	//BUSCAR TODOS--------------------------------------------------------------------
	public List<Cliente>buscarTodos(){
		return repo.findAll();
	}
	
	
	//BUSCAR TODOS PAGINADO ------------------------------------------------------
		public Page<Cliente>buscarPagina(Integer pg,Integer linhasPorPagina, String ordem, String direcao){
			PageRequest pageRequest= PageRequest.of(pg, linhasPorPagina, Direction.valueOf(direcao), ordem);
			return repo.findAll(pageRequest);
			
			
		}
		
	
	/*
	//INSERT -----------------------------------------------------------------------------
	//pra garantir que seja INSERT (metodo repo.save precisa saber q o ID é null
	public Cliente insert(Cliente obj) {
		obj.setId(null); 
		return repo.save(obj);
	}
	*/
		
		
		
	//UPDATE ------------------------------------------------------------------------------
	//pra garantir que seja UpdateT (metodo repo.save precisa saber q o ID NAO é null
	public Cliente update(Cliente obj) {

		Cliente newObj= buscar(obj.getId());
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
		buscar(obj.getId());		
		return repo.save(obj);
	}
	
	
	
	//DELETE ------------------------------------------------------------------------------
	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR PORQUE HA ENTIDADES RELACIONADAS : ENDERECO E/OU PEDIDO");
		}
	}
	
	
	
	//TRANSFORMA DTO em OBJ ----------------------------------------------------------------------
	public Cliente transformaDtoEmObj(ClienteDTO dto) {
		Cliente cliente= new Cliente(dto.getId(),dto.getNome(),dto.getEmail(),null,null);
		return cliente;
		
		
	}
	
}
