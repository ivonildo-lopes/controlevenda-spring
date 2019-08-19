package com.arquitetura.security;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generatetoken(String user) {
		return Jwts.builder()
				.setSubject(user)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		
		if(Objects.nonNull(claims)) {
			String user = claims.getSubject();
			Date dataExpiration = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			
			if(Objects.nonNull(user) && Objects.nonNull(dataExpiration) && now.before(dataExpiration)) {
				return true;
			}
		}
		
		return false;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}catch(Exception e) {
			return null;
		}
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		
		if(Objects.nonNull(claims)) {
			return claims.getSubject();
		}
		return null;
	}

}
