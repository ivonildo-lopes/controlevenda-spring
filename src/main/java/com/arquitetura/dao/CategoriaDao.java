package com.arquitetura.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arquitetura.model.Categoria;

@Repository
public interface CategoriaDao extends JpaRepository<Categoria, Long> {

}
