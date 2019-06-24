package com.map.primeiroprojeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.map.primeiroprojeto.domain.Categoria;

 
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
