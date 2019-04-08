package com.arquitetura.DTO;

import java.util.Date;

import lombok.Data;

public @Data class EntradaSaidaConsultaDto {

	private Long id;
	private String tipo;
	private Double valor;
	private String cliente;
	private String veiculo;
	private Date data;
	
}
