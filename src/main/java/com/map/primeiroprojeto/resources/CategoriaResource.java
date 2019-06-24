package com.map.primeiroprojeto.resources;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.map.primeiroprojeto.domain.Categoria;
import com.map.primeiroprojeto.services.CategoriaService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService servico;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Categoria cat= servico.buscar(id);				
		return ResponseEntity.ok().body(cat) ;
			
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
