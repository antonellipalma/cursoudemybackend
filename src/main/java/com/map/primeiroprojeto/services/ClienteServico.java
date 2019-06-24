package com.map.primeiroprojeto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.map.primeiroprojeto.domain.Cliente;
import com.map.primeiroprojeto.repositories.ClienteRepository;
import com.map.primeiroprojeto.services.exception.ObjectNotFoundException;

@Service
public class ClienteServico {
	
	
	@Autowired
	ClienteRepository repoCli;
	
	public Cliente buscar(Integer id) {
		
		Optional<Cliente> cliente= repoCli.findById(id);
		return cliente.orElseThrow( ()-> new ObjectNotFoundException("CLIENTE NAO ENCONTRADO- ERRO: "+ "TIPO: "+ Cliente.class.getName())    );
		
	}

}
