package com.arquitetura.service;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.arquitetura.DTO.ComboEntradaSaidaDto;
import com.arquitetura.DTO.EntradaSaidaConsultaDto;
import com.arquitetura.DTO.EntradaSaidaDto;

@NoRepositoryBean
public interface EntradaSaidaService {
	
	ComboEntradaSaidaDto populaListas();
	ComboEntradaSaidaDto populaListasConsulta();
	
	EntradaSaidaDto save(EntradaSaidaDto dto);
	
	List<EntradaSaidaConsultaDto> findByEntradaSaida(EntradaSaidaDto dto);



}
