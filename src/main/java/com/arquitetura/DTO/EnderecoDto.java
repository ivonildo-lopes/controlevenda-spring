package com.arquitetura.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

public @Data class EnderecoDto {

	private Long id;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cep;

	private CidadeDto cidade;
	
	@JsonIgnore
	private ClienteDto cliente;
}
