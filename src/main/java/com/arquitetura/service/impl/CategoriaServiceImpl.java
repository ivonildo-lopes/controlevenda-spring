package com.arquitetura.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquitetura.dao.CategoriaDao;
import com.arquitetura.error.BadValueException;
import com.arquitetura.model.Categoria;
import com.arquitetura.service.CategoriaService;

@Service(value = "categoriaService")
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	private CategoriaDao dao;
	
	public Categoria findById(Long id) {
		
		Categoria obj = this.dao.findById(id).orElse(null);
		
		if(Objects.isNull(obj)) {
				throw new BadValueException("Não existe categoria com esse Id");
		}
		
		return obj;
	}

}
