package com.arquitetura.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "produto")
public @Data class Produto implements Serializable {

	private static final long serialVersionUID = 1166567835458029339L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private Double preco;
	
	@ManyToMany
	@JoinTable(name = "produto_categoria",
				joinColumns = @JoinColumn(name="id_produto"),
				inverseJoinColumns = @JoinColumn(name = "id_categoria")
			)
	@JsonIgnore
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	public Produto() {}

	public Produto(Long id, String nome, Double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	

}
