package com.arquitetura.error;

public class AuthorizationException extends RuntimeException {

	
	private static final long serialVersionUID = -3984700257289650795L;
	public AuthorizationException(String message) {super(message);};
	public AuthorizationException(String message, Throwable cause) {super(message, cause);};

}
