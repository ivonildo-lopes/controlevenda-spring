package com.arquitetura.DTO;

import java.util.List;

import lombok.Data;

public @Data class ProdutoDto {

	private Long id;
	private String nome;
	private Double preco;
	private List<CategoriaDto> categorias;
}
