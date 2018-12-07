package com.arquitetura.DTO;

import java.util.List;

import lombok.Data;

public @Data class CategoriaDto {

	private Long id;
	
	private String nome;
	
	private List<ProdutoDto> produtos;
}
