package com.arquitetura.service;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AuthService {
	
	void sendNewPassword(String user);

}
