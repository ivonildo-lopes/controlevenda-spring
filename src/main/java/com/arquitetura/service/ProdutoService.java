package com.arquitetura.service;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.arquitetura.DTO.ProdutoDto;
import com.arquitetura.model.Produto;

@NoRepositoryBean
public interface ProdutoService {
	
	Produto findById(Long id);

	List<Produto> findAll();

	ProdutoDto save(ProdutoDto produtoDto);
	
	List<ProdutoDto> saveListProduto(List<ProdutoDto> produtoDto);



}
