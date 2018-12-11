package com.arquitetura.model.enums;

import java.util.Objects;

import com.arquitetura.error.BadValueException;

public enum TipoCliente {

	
	PESSOAFISICA(1,"pessoa fisica"),
	PESSOAJURIDICA(2,"pessoa juridica");
	
	private String descricao;
	private Integer codigo;
	
	TipoCliente(Integer codigo,String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	

	public Integer getCodigo() {
		return codigo;
	}
	
	public static TipoCliente toEnum(Integer codigo) {
		if(Objects.isNull(codigo))
			return null;
		
		for( TipoCliente tipoCliente : TipoCliente.values()) {
			if(codigo.equals(tipoCliente.getCodigo())) {
				return tipoCliente;
			}
		}
		
		throw new BadValueException("Não existe esse tipo de Cliente");
		
	}
}
