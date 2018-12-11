package com.arquitetura.model.enums;

public enum EstadoPagamento {
	
	PENDENTE(1,"Pendente"),
	QUITADO(2,"Cancelado"),
	CANCELADO(3,"Quitado");
	
	private Integer codigo;
	private String descricao;
	
	private EstadoPagamento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
