package com.arquitetura.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name = "estado")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public @Data class Estado implements Serializable {

	private static final long serialVersionUID = 5958756653816230783L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length=250)
	private String nome;
	
	private String sigla;
	
	@OneToMany(mappedBy="estado")
	@JsonIgnore
	private List<Cidade> cidades;
	
	public Estado() {}

	public Estado(Long id, String nome, String sigla) {
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
	}
	
	

}
