package com.arquitetura.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.arquitetura.model.enums.EstadoPagamento;

import lombok.Data;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public @Data abstract class Pagamento implements Serializable{
	

	private static final long serialVersionUID = -6468928476888776071L;

	@Id
	private Long id;
	
	private EstadoPagamento estadoPagamento;
	
	@OneToOne
	@JoinColumn(name = "id_pedido")
	@MapsId
	private Pedido pedido;

}
