package com.arquitetura.service;

import org.springframework.data.repository.NoRepositoryBean;

import com.arquitetura.DTO.PromissoriaDto;

@NoRepositoryBean
public interface PromissoriaService {
	
//	ComboEntradaSaidaDto populaListas();
//	ComboEntradaSaidaDto populaListasConsulta();
	
	void save(PromissoriaDto dto);
	
//	List<EntradaSaidaConsultaDto> findByEntradaSaida(EntradaSaidaDto dto);



}
