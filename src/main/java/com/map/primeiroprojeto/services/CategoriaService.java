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
		
	
		//versao do instrutor	
		//return obj.orElseThrow(() -> new ObjectNotFoundException(
		//"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

		
	}
	
	
	
	/*FIND -OPCAP 02 - COM Service / SEM Repository - */
	/*------------------------------------------------------------
	public Categoria buscar(List<Categoria> lst, Integer id) {		
		for(Categoria categoriaLista: lst) {
			if(categoriaLista.getId().equals(id)) {
				return categoriaLista;
			}
		}
		return null;	
	}
	------------------------------------------------------------*/
	
	
	
	
}
