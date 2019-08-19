package com.arquitetura.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.arquitetura.model.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table
@Data
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 6232721910622599338L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String user;
	
	@JsonIgnore
	private String password;
	
	private boolean ativo;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfis")
	private Set<Integer> perfis = new HashSet<>();
	
	public Set<Perfil> getPerfis(){
		return perfis.stream().map(perfil -> Perfil.toEnum(perfil)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getId());
	}
	
	public void addPerfils(List<Perfil> perfis) {
		this.perfis.addAll(perfis.stream().map(p -> p.getId()).collect(Collectors.toSet()));
	}
}