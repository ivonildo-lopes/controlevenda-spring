package com.arquitetura.error;

public class BadValueException extends RuntimeException {

	private static final long serialVersionUID = 5360845281651474914L;
	
	public BadValueException(String message) {super(message);};
	public BadValueException(String message, Throwable cause) {super(message, cause);};

}
