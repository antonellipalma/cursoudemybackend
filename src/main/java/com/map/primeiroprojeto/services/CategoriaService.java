package com.map.primeiroprojeto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.map.primeiroprojeto.domain.Categoria;
import com.map.primeiroprojeto.repositories.CategoriaRepository;
import com.map.primeiroprojeto.services.exception.ObjectNotFoundException;

//import javassist.tools.rmi.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	 
	public Categoria buscar(Integer id) {		
		Optional<Categoria> cat =repo.findById(id);		
		//Opcao de retorno 01 - retorno simples sem tratamento de excecao
		//return cat.orElse(null);
		
		//Opcao de retorno 02 - com excecao Padrao - usando classe RuntimeException
		//return cat.orElseThrow(() -> new RuntimeException("OPT:02: Deu pau - Objeto nao Encontrado! Id: "+id+" - Tipo: "+ Categoria.class.getName()) );
	
		//Opcao de retorno 03 - com excecao usando classe ObjectNotFoundException Personalizada do MEU PACOTE (services.exception.ObjectNotFoundException;)
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
	
	
}
