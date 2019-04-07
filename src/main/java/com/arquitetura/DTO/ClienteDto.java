package com.arquitetura.DTO;

import java.util.Date;

import lombok.Data;

public @Data class ClienteDto {

	private Long id;

	private String nome;

	private String email;

	private String cpf;

	private String rg;

	private String telefone;

	private String endereco;

	private String cep;

	private Date dataNascimento;

}
