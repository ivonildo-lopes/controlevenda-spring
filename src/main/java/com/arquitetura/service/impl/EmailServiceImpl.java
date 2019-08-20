package com.arquitetura.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.arquitetura.model.Usuario;
import com.arquitetura.service.EmailService;

@Service
public class EmailServiceImpl  implements EmailService{

	@Value("${default.sender}")
	private String from;
	
	@Autowired
	private MailSender mailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Override
	public void sendConfirmationEmailNewUser(Usuario usuario) {
		SimpleMailMessage msg = prepareSimpleMailMessageFromNewUser(usuario);
		this.sendEmail(msg);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromNewUser(Usuario usuario) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(usuario.getUser());
		msg.setFrom(from);
		msg.setSubject("Usuario Cadastrado " + usuario.getUser());
		msg.setSentDate(new Date(System.currentTimeMillis()));
		msg.setText(usuario.toString());
		return msg;
	}
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando email");
		mailSender.send(msg);
		LOG.info("Email Enviado com sucesso");
		
	}

}
