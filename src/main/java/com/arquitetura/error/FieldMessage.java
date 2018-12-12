package com.arquitetura.error;

import java.io.Serializable;

import lombok.Data;

public @Data class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1757369669282886314L;
	
	private String fieldName;
	private String message;
	
	public FieldMessage() {}
	
	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}
	
	
	

}
