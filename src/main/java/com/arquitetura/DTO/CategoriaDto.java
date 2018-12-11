package com.arquitetura.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

public @Data class CategoriaDto {

	private Long id;
	
	private String nome;
	
//	@JsonIgnore
	private List<ProdutoDto> produtos;
}
