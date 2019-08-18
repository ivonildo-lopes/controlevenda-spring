package com.arquitetura.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.arquitetura.model.enums.Perfil;

import lombok.Data;

@Data
public class UsuarioDto implements Serializable {

	private static final long serialVersionUID = -6969834748402921416L;
	
	private Long id;
	private String user;
	private String password;
	private boolean ativo;
	private List <Perfil> listaPerfis = new ArrayList<>(); //perfil 
	private Set<Perfil> perfis = new HashSet<>(); // recuperaPErfil
}
