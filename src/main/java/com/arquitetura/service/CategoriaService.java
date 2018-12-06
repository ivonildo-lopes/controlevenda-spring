package com.arquitetura.service;

import org.springframework.data.repository.NoRepositoryBean;

import com.arquitetura.model.Categoria;

@NoRepositoryBean
public interface CategoriaService {
	
	Categoria findById(Long id);

}
