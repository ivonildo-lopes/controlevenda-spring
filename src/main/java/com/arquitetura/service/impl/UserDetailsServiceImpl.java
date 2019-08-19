package com.arquitetura.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arquitetura.dao.UsuarioDao;
import com.arquitetura.model.Usuario;
import com.arquitetura.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUser(username).orElse(null);
		
		if(Objects.isNull(usuario))
			throw new UsernameNotFoundException("this User not found! " + username);
		
		return new UserSpringSecurity(usuario.getId(), usuario.getUser(), usuario.getPassword(), usuario.getPerfis());
	}
	
}
