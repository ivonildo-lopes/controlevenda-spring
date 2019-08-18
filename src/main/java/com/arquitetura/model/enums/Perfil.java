package com.arquitetura.model.enums;

import java.util.Objects;

public enum Perfil {
	
	ADMIN(1,"ROLE_ADMIN"),
	VENDEDOR(2,"ROLE_VENDEDOR"),
	ORCAMENTO(3,"ROLE_ORCAMENTO"),
	FUNCIONARIO(4,"ROLE_FUNCIONARIO");
	
	private Integer id;
	private String descricao;
	
	private Perfil(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	
	public Integer getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer id) {
		
		if (Objects.isNull(id)) return null;
		
		for (Perfil perfil : Perfil.values()) {
			if(id.equals(perfil.getId())) {
				return perfil;
			}
			
		}
		throw new IllegalArgumentException("Id invalid " + id);
	}
}
