package com.map.primeiroprojeto.resources;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.map.primeiroprojeto.domain.Cliente;
import com.map.primeiroprojeto.services.ClienteServico;
import com.map.primeiroprojeto.services.exception.ObjectNotFoundException;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource implements Serializable {

	@Autowired
	ClienteServico servico;
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id)throws ObjectNotFoundException {
		Cliente cli= servico.buscar(id);
		return ResponseEntity.ok(cli);
		
	}
	
	
}
