package com.arquitetura.service;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.mail.SimpleMailMessage;

import com.arquitetura.model.Usuario;

@NoRepositoryBean
public interface EmailService {
	
	void sendConfirmationEmailNewUser(Usuario usuario);
	
	void sendConfimationEmailNewPassword(String usuario, String newPassword);
	
	void sendEmail(SimpleMailMessage msg);

}
