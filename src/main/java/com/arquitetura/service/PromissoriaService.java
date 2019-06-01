package com.arquitetura.service;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.arquitetura.DTO.PromissoriaDto;
import com.arquitetura.DTO.PromissoriaConsultaDto;

@NoRepositoryBean
public interface PromissoriaService {
	
//	ComboEntradaSaidaDto populaListas();
//	ComboEntradaSaidaDto populaListasConsulta();
	
	void save(PromissoriaDto dto);
	
	List<PromissoriaDto> findByPromissoria(PromissoriaConsultaDto dto);

	void atualizaPromissoriaPaga(Long id);


}
