package com.arquitetura.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;

import com.arquitetura.DTO.CategoriaDto;
import com.arquitetura.model.Categoria;

@NoRepositoryBean
public interface CategoriaService {
	
	Categoria findById(Long id);

	CategoriaDto save(CategoriaDto categoriaDto);

	List<Categoria> findAll();

	List<CategoriaDto> saveListCategoria(List<CategoriaDto> categoriaDto);

	List<CategoriaDto> findByIdCategorias(List ids);

	List<CategoriaDto> getCategoriasDto(List<Categoria> categorias);

	List<Categoria> getCategorias(List<CategoriaDto> findByIdCategorias);

	CategoriaDto update(Long id, CategoriaDto categoriaDto);

	void delete(Long id);
	
	Page<Categoria> findPage(Integer page, Integer linePerPage, String orderBy, String direction);

}
