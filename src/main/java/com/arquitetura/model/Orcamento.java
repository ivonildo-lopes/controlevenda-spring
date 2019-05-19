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

@Entity
@Table(name = "orcamento")
@Data
public class Orcamento implements Serializable {

	private static final long serialVersionUID = 7240375519342273219L;

	@Id
	@GeneratedValue
	private Long id;
	
	private Double valor;
	
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="id_veiculo", referencedColumnName="id")
	private Veiculo veiculo;
	
	private Date data;
}
