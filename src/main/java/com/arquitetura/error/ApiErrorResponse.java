package com.arquitetura.error;

import java.util.List;

import com.google.common.collect.Lists;

public class ApiErrorResponse {
	
	private int status;
    private int code;
    private List<String> errors;

    public ApiErrorResponse(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.errors = Lists.newArrayList(message);
    }
    public ApiErrorResponse(int status, int code, List<String> errors) {
        this.status = status;
        this.code = code;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "ApiErrorResponse{" +
                "status=" + status +
                ", code=" + code +
                ", errors=" + errors +
                '}';
    }

}
