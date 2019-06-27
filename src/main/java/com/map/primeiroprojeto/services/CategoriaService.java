package com.map.primeiroprojeto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.map.primeiroprojeto.domain.Categoria;
import com.map.primeiroprojeto.repositories.CategoriaRepository;
import com.map.primeiroprojeto.services.exception.DataIntegrityException;
import com.map.primeiroprojeto.services.exception.ObjectNotFoundException;

//import javassist.tools.rmi.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	 
	public Categoria buscar(Integer id) {		
		Optional<Categoria> cat =repo.findById(id);				
		return cat.orElseThrow(	() -> new ObjectNotFoundException(
				"OPT 03: Objeto nao encontrado! Id: "+ id+" -  TIPO: "+ Categoria.class.getName() )  );
		
		
	}
	
	
	//INSERT -----------------------------------------------------------------------------
	//pra garantir que seja INSERT (metodo repo.save precisa saber q o ID é null
	public Categoria insert(Categoria obj) {
		obj.setId(null); 
		return repo.save(obj);
	}
	
	//UPDATE ------------------------------------------------------------------------------
	//pra garantir que seja UpdateT (metodo repo.save precisa saber q o ID NAO é null
	public Categoria update(Categoria obj) {
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
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR UMA CATEGORIA QUE POSSUI PRODUTOS");
		}
	}
}
