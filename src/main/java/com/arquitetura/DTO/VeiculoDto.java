package com.arquitetura.DTO;

import java.io.Serializable;
import java.util.Objects;

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
	
	
	
	private Double valorEntrada;
	private Double valorGasto;
	
	
	public Double getValorTotalVeiculo() {
		return this.getValorEntrada()  + this.getValorGasto();
	}
	
	public Double getValorEntrada() {
		return Objects.isNull(this.valorEntrada) ? 0 : valorEntrada;
	}
	
	public Double getValorGasto() {
		return Objects.isNull(this.valorGasto) ? 0 : this.valorGasto;
	}
}
