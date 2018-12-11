package com.arquitetura.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.arquitetura.model.enums.TipoCliente;

import lombok.Data;

@Entity
@Table(name = "cliente")
public @Data class Cliente implements Serializable {
	
	private static final long serialVersionUID = 2817361130657753399L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String cpfOuCnpj;
	
	private TipoCliente tipoCliente;
	
	@OneToMany(mappedBy="cliente", fetch= FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	@ElementCollection
	@CollectionTable(name = "telefone")
	private Set<String> telefones = new HashSet<>();

}
