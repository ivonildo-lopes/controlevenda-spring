package com.arquitetura.DTO;

import java.util.Date;

import lombok.Data;

public @Data class EntradaSaidaDto {

	private String tipo;
	private Double valor;
	private ClienteDto cliente;
	private VeiculoDto veiculo;
	private Date data;
	
	//para consulta
	private Long IdVeiculo;
	private Long IdCliente;
	private Date dataInicio;
	private Date dataFim;

}
