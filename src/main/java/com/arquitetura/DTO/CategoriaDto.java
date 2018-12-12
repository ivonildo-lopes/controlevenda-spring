package com.arquitetura.DTO;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

public @Data class CategoriaDto {

	private Long id;
	
	
	@NotEmpty(message = "Preencha o nome da categoria")
	@Length(min = 5, max = 80, message = "O nome da categoria deve conter entre {min} e {max} caracteres")
	private String nome; 
	
//	@JsonIgnore
	private List<ProdutoDto> produttos;
}
