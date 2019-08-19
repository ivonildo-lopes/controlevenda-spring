package com.arquitetura.service.impl;

import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arquitetura.dao.UsuarioDao;
import com.arquitetura.error.BadValueException;
import com.arquitetura.model.Usuario;
import com.arquitetura.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	private Random random = new Random();

	@Override
	public void sendNewPassword(String user) {
		
		Usuario usuario = usuarioDao.findByUser(user).orElse(null);
		
		if(Objects.isNull(usuario)) {
			throw new BadValueException("Usuario não cadastrado!");
		}
		
		String password = newPassword();
		usuario.setPassword(bcrypt.encode(password));
		
		usuarioDao.save(usuario);
		
	}

	private String newPassword() {
		
		char vet[] = new char[10];
		
		for (int i = 0; i < 10; i++) {
			vet[i] = this.randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		
		int opcao = random.nextInt(3);
		
		if(opcao == 0) { //gera digitp
			return (char) (random.nextInt(10) + 48);
		}else if (opcao == 1) { //gera letras MAISCULA
			return (char) (random.nextInt(26) + 65);
		}else { // gera letras minuscula
			return (char) (random.nextInt(26) + 97);
		}
	}

}
