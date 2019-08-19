package com.arquitetura.service;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.arquitetura.DTO.UsuarioDto;
import com.arquitetura.model.Usuario;

@NoRepositoryBean
public interface UsuarioService {
	
	Usuario findById(Long id);
	
	Usuario findByUser(String user);

	UsuarioDto save(UsuarioDto usuarioDto);

	List<Usuario> findAll();
	
	UsuarioDto alterar(Long id, UsuarioDto usuarioDto);

	List<UsuarioDto> findByUsuarios(UsuarioDto dto);



}
