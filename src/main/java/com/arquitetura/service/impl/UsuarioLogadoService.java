package com.arquitetura.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;

import com.arquitetura.security.UserSpringSecurity;

public class UsuarioLogadoService {
	
	public static UserSpringSecurity authenticaded() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception e) {
			return null;
		}
	}

}
