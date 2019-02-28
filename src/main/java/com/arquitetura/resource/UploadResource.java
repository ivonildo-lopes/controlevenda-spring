package com.arquitetura.resource;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.arquitetura.service.impl.UploadServiceImpl;

@RestController
@RequestMapping(value = "/upload")
public class UploadResource implements Serializable {

	private static final long serialVersionUID = -7602920368230305256L;

	@Autowired
	private UploadServiceImpl service;
	
	@PostMapping
	public void upload(@RequestParam MultipartFile arquivo) {
		service.salvarFoto(arquivo);
	}
}
