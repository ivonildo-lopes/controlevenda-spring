package com.arquitetura.DTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.arquitetura.model.enums.TipoCliente;

import lombok.Data;

public @Data class ClienteDto {

	private Long id;

	private String nome;

	private String email;

	private String cpfOuCnpj;

	private TipoCliente tipoCliente;

	private List<EnderecoDto> enderecos = new ArrayList<EnderecoDto>();

	private Set<String> telefones = new HashSet<>();

}
