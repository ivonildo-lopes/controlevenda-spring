package com.arquitetura.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arquitetura.DTO.Response;
import com.arquitetura.security.JWTUtil;
import com.arquitetura.security.UserSpringSecurity;
import com.arquitetura.service.impl.UsuarioLogadoService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public Response refreshToken(HttpServletResponse response) {
		UserSpringSecurity user = UsuarioLogadoService.authenticaded();
		String token = jwtUtil.generatetoken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return new Response().setData(null).setStatus(HttpStatus.NO_CONTENT);
	}

}