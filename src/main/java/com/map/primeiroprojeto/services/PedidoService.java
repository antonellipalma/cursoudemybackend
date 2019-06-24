package com.map.primeiroprojeto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.map.primeiroprojeto.domain.Pedido;
import com.map.primeiroprojeto.repositories.PedidoRepository;
import com.map.primeiroprojeto.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	
	@Autowired
	PedidoRepository repoPedi;
	
	public Pedido buscar(Integer id) {
		
		Optional<Pedido> pedido= repoPedi.findById(id);
		return pedido.orElseThrow( ()->new ObjectNotFoundException("PEDIDO NAO ENCONTRADO - ERRO:"+"TIPO: "+ Pedido.class.getName()) );
		
		
	}
	
}
