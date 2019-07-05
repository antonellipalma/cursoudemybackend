package com.map.primeiroprojeto.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.map.primeiroprojeto.domain.Cliente;
import com.map.primeiroprojeto.dto.ClienteDTO;
import com.map.primeiroprojeto.services.ClienteServico;
import com.map.primeiroprojeto.services.exception.ObjectNotFoundException;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource  {

	@Autowired
	ClienteServico servico;
	
	//FIND por ID -------------------------------------------------------
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id)throws ObjectNotFoundException {
		Cliente cli= servico.buscar(id);
		return ResponseEntity.ok(cli);
		
	}
	
	
	//*****************
	

	
	//FIND ALL ------------------------------------------------------------
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>>findAll(){
		List<Cliente> lst= servico.buscarTodos();
		List<ClienteDTO> lstDTO= lst.stream().map(obj->new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(lstDTO);
	}
	

	
	
	
	//FIND ALL PAGINADO ------------------------------------------------------
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>>findAllPaginado(
			@RequestParam(value="page", defaultValue="0") Integer pg,
			@RequestParam(value="linhas",defaultValue="24") Integer linhasPorPagina,
			@RequestParam(value="ordem",defaultValue="nome") String  ordem,
			@RequestParam(value="direcao",defaultValue="ASC") String direcao
			){
		Page<Cliente>lst=servico.buscarPagina(pg, linhasPorPagina, ordem, direcao);
		Page<ClienteDTO>lstDTO= lst.map(obj->new ClienteDTO(obj));
		return ResponseEntity.ok().body(lstDTO);
		
	}
	
	
	
	/*
	//INSERIR --------------------------------------------------------------
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void>insert(@Valid @RequestBody ClienteDTO objDTO){
		Cliente obj=servico.transformaDtoEmObj(objDTO);		
		obj=servico.insert(obj);
		URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	*/
	
	
	//ATUALIZAR -------------------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void>update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
			
		Cliente obj=servico.transformaDtoEmObj(objDTO);		
		obj.setId(id);
		obj=servico.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	
	//DELETAR --------------------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void>delete(@PathVariable Integer id){
		servico.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	
	
}
