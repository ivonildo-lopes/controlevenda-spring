package com.arquitetura.DTO;

import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Response<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3071022169890635195L;
	private T data;
	private List<String> errors;
	private List<String> warns;
	private List<String> infos;
	private URI uri;

	private Integer status;

	public Response() {

	}

	public T getData() {
		return data;
	}

	public Response<T> setData(T data) {
		this.data = data;
		return this;
	}

	public List<String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<>();
		}
		return errors;
	}

	public Response<T> setErrors(List<String> errors) {
		this.errors = errors;
		return this;
	}

	public Response<T> setErrors(String... errors) {
		this.errors = Lists.newArrayList(errors);
		return this;
	}

	public Response<T> setWarns(String... warns) {
		this.warns = Lists.newArrayList(warns);
		return this;
	}

	public Response<T> setWarns(List<String> warns) {
		this.warns = warns;
		return this;
	}

	public List<String> getWarns() {
		if (this.warns == null) {
			this.warns = new ArrayList<>();
		}
		return warns;
	}

	public Response<T> setInfos(String... infos) {
		this.infos = Lists.newArrayList(infos);
		return this;
	}

	public Response<T> setInfos(List<String> infos) {
		this.infos = infos;
		return this;
	}

	public List<String> getInfos() {
		if (this.infos == null) {
			this.infos = new ArrayList<>();
		}
		return infos;
	}

	public Integer getStatus() {
		return status;
	}

	public Response<T> setStatus(HttpStatus status) {
		this.status = status.value();
		return this;
	}

	public URI  getUri() {
		return uri;
	}

	public Response<T>  setUri(URI uri) {
		this.uri = uri;
		return this;
	}
	
	

}