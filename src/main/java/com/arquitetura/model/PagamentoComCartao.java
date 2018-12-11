package com.arquitetura.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = false)
public @Data class PagamentoComCartao extends Pagamento {
	
	private static final long serialVersionUID = -7836557976591812910L;

	private Integer numeroDeParcelas;
	
	

}
