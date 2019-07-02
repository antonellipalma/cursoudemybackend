package com.map.primeiroprojeto.resources;


import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.map.primeiroprojeto.domain.Categoria;
import com.map.primeiroprojeto.dto.CategoriaDTO;
import com.map.primeiroprojeto.services.CategoriaService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService servico;
	
	//FIND por ID -------------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Categoria cat= servico.buscar(id);				
		return ResponseEntity.ok().body(cat) ;
			
	}
	
	//FIND ALL ------------------------------------------------------------
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>>findAll(){
		List<Categoria> lst= servico.buscarTodos();
		List<CategoriaDTO> lstDTO= lst.stream().map(obj->new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(lstDTO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//FIND ALL PAGINADO ------------------------------------------------------
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>>findAllPaginado(
			@RequestParam(value="page", defaultValue="0") Integer pg,
			@RequestParam(value="linhas",defaultValue="24") Integer linhasPorPagina,
			@RequestParam(value="ordem",defaultValue="nome") String  ordem,
			@RequestParam(value="direcao",defaultValue="ASC") String direcao
			){
		Page<Categoria>lst=servico.buscarPagina(pg, linhasPorPagina, ordem, direcao);
		Page<CategoriaDTO>lstDTO= lst.map(obj->new CategoriaDTO(obj));
		return ResponseEntity.ok().body(lstDTO);
		
	}
	
	
	
	
	//INSERIR --------------------------------------------------------------
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void>insert(@Valid @RequestBody CategoriaDTO objDTO){
		Categoria obj=servico.transformaDtoEmObj(objDTO);		
		obj=servico.insert(obj);
		URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	//ATUALIZAR -------------------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void>update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id){
		Categoria obj=servico.transformaDtoEmObj(objDTO);
		obj.setId(id);
		obj=servico.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void>delete(@PathVariable Integer id){
		servico.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	
	
	
	/*--------------------------------------------------
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria cat1= new Categoria(1,"Informatica");
		Categoria cat2= new Categoria(2,"Escritorio");
		
		List<Categoria> lst= new ArrayList();
		lst.add(cat1);
		lst.add(cat2);
				
		return lst ;			
	}	
	--------------------------------------------------*/
	
	
	/*FIND -OPCAP 01 -SIMPLES SEM Service/Repository - na mao*/
	/*--------------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Categoria find(@PathVariable Integer id) {
		Categoria cat1= new Categoria(1,"Informatica");
		Categoria cat2= new Categoria(2,"Escritorio");
		
		List<Categoria> lst= new ArrayList();
		lst.add(cat1);
		lst.add(cat2);
		
		for(Categoria categoriaLista: lst) {
			if(categoriaLista.getId().equals(id)) {
				return categoriaLista;
			}
		}
				
		return null ;			
	}
	---------------------------------------------------------*/
	
	
	
	/*FIND -OPCAP 02 -SIMPLES COM Service / SEM Repository*/
	/*---------------------------------------------------------------
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Categoria find(@PathVariable Integer id) {
		Categoria cat1= new Categoria(1,"Informatica");
		Categoria cat2= new Categoria(2,"Escritorio");
	
		List<Categoria> lst= new ArrayList();
		lst.add(cat1);
		lst.add(cat2);
	
		servico.buscar(lst, id);				
		return servico.buscar(lst, id) ;
			
	}
	---------------------------------------------------------------*/
	
	
	
	
	 
}
