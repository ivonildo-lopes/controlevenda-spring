package com.arquitetura.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.arquitetura.model.enums.Perfil;

import lombok.Data;

@Data
public class UserSpringSecurity implements UserDetails {

	private static final long serialVersionUID = -6090167451154589768L;
	
	private Long id;
	private String user;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSpringSecurity() {	}
	
	public UserSpringSecurity(Long id, String user, String password,
			Set<Perfil> perfis) {
		super();
		this.id = id;
		this.user = user;
		this.password = password;
		this.authorities = perfis.stream()
								.map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao())).collect(Collectors.toList());
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.user;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean hasRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}
	
	public boolean nonHasRole(Perfil perfil) {
		return !hasRole(perfil);
	}

}
