package com.arquitetura.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "entradaSaida")
public class EntradaSaida implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String tipo;
	private Double valor;
	@ManyToOne
	@JoinColumn(name="id_cliente", referencedColumnName="id")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name="id_veiculo", referencedColumnName="id")
	private Veiculo veiculo;
	private Date data;
}
