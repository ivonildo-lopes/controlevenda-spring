package com.arquitetura.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "categoria")
public @Data class Categoria implements Serializable {

	private static final long serialVersionUID = 7899816091922485160L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length=250)
	private String nome;
		
	public Categoria() {}

	public Categoria(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	

}
