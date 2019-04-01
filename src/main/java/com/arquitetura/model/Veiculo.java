package com.arquitetura.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable {

	private static final long serialVersionUID = -1299709278480523009L;
	
	@Id
	@GeneratedValue
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
