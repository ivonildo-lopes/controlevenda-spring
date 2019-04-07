package com.arquitetura.service;

import org.springframework.data.repository.NoRepositoryBean;

import com.arquitetura.DTO.ComboEntradaSaidaDto;
import com.arquitetura.DTO.EntradaSaidaDto;

@NoRepositoryBean
public interface EntradaSaidaService {
	
	ComboEntradaSaidaDto populaListas();
	
	EntradaSaidaDto save(EntradaSaidaDto dto);



}
