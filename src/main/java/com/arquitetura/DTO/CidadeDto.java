package com.arquitetura.DTO;

import lombok.Data;

public @Data class CidadeDto {

	private Long id;
	
	private String nome;
	
	private EstadoDto estadoDto;
}
