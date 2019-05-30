package com.arquitetura.DTO;

import java.util.Date;

import lombok.Data;

public @Data class PromissoriaDto {

	private ClienteDto cliente;
	private Double valor;
	private Date data;
	private Integer qtdParcela;
	
	
	private Long idCliente;
	private String nomeCliente;
	
	private Long idPromissoria;
	private String descricao;
	private Boolean isPago;
	
}
