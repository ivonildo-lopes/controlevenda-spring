package com.arquitetura.error;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends ApiErrorResponse {
	
	private List<FieldMessage> listErro = new ArrayList<FieldMessage>();

	public ValidationError(int status, int code, List<String> errors) {
		super(status, code, errors);
	}

	public void addErro(String fieldName, String message) {
		listErro.add(new FieldMessage(fieldName, message));
	}
	
	public List<FieldMessage> getListErro(){
		return this.listErro;
	}
}
