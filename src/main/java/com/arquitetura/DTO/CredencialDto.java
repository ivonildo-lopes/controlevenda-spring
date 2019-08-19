package com.arquitetura.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class CredencialDto implements Serializable {
	
	private static final long serialVersionUID = -3475486919227550022L;

	private String user;
	private String password;

}
