package com.arquitetura.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "estado")
public @Data class Estado implements Serializable {

	private static final long serialVersionUID = 5958756653816230783L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length=250)
	private String nome;
	
	private String sigla;
	
	@OneToMany(mappedBy="estado")
	private List<Cidade> cidades;
	
	public Estado() {}

	public Estado(Long id, String nome, String sigla) {
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
	}
	
	

}
