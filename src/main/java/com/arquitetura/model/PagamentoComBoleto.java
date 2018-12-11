package com.arquitetura.model;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = false)
public @Data class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = -300457337234637270L;

	private Date dataVencimento;

	private Date dataPagamento;

}
