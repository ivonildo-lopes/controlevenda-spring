package com.arquitetura.service;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.multipart.MultipartFile;

@NoRepositoryBean
public interface UploadService {
	
	void enviarArquivo(MultipartFile arquivo);

}
