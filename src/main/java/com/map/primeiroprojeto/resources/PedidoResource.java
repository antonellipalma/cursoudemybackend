package com.map.primeiroprojeto.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.map.primeiroprojeto.domain.Pedido;
import com.map.primeiroprojeto.services.PedidoService;
import com.map.primeiroprojeto.services.exception.ObjectNotFoundException;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	PedidoService servico;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?>find(@PathVariable Integer id)throws ObjectNotFoundException{
		Pedido pedido = servico.buscar(id);
		return ResponseEntity.ok(pedido);
				
	}
	
}
