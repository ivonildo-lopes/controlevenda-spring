package com.arquitetura.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name = "endereco")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public @Data class Endereco implements Serializable {
	
	private static final long serialVersionUID = -7002484715534029760L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String logradouro;
	
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	private String cep;
	
	@ManyToOne()
	@JoinColumn(name="id_cliente", referencedColumnName="id")
	@JsonIgnore
	private Cliente cliente;
	
	@ManyToOne()
	@JoinColumn(name="id_cidade")
	private Cidade cidade;
	

}
