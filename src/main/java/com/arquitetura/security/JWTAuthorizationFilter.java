package com.arquitetura.security;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private JWTUtil jwtUtil;

	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager
				, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	
	/**
	 * filtro que autoriza de o usuario tem acesso ou não ao recurso solicitado
	 */
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res
			, FilterChain chain)throws IOException, ServletException {
		
		String header = req.getHeader("Authorization");
		
		if(Objects.nonNull(header) && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken authToken = getAuthentication( header.substring(7));
			
			if(Objects.nonNull(authToken)) {
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {

		if(jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
		}
		return null;
	}

	
	
}
