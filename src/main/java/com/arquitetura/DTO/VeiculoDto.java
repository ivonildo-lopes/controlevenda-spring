package com.arquitetura.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class VeiculoDto implements Serializable {

	private static final long serialVersionUID = 6945068978785569172L;
	
	private Long id;
	private String placa;
	private String renavam;
	private String chassi;
	private String modelo;
	private Integer anoModelo;
	private Integer anoFabricacao;
	private String cor;
	private String observacao;
	private Boolean isVendido;
}
