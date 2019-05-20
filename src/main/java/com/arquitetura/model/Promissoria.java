package com.arquitetura.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "promissoria")
public class Promissoria implements Serializable {
	
	private static final long serialVersionUID = 2209044580241059486L;

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_cliente", referencedColumnName="id")
	private Cliente cliente;
	
	private Double Valor;
	
	private Date data;
	
	private String descricao;
	
	private Boolean isPago;

}
