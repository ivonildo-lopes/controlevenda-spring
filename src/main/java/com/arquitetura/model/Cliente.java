package com.arquitetura.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "cliente")
@Data 
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 2817361130657753399L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String cpf;
	
	private String rg;
	
	private String telefone;
	
	private String endereco;
	
	private String cep;
	
	private Date dataNascimento;
	

}
