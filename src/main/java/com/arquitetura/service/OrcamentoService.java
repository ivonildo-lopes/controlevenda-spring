package com.arquitetura.service;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.arquitetura.DTO.ComboEntradaSaidaDto;
import com.arquitetura.DTO.OrcamentoConsultaDto;
import com.arquitetura.DTO.OrcamentoDto;

@NoRepositoryBean
public interface OrcamentoService {
	
	ComboEntradaSaidaDto populaListas();
	ComboEntradaSaidaDto populaListasConsulta();
	
	OrcamentoDto save(OrcamentoDto dto);
	
	void saveAll(List<OrcamentoDto> orcamentos);
	List<OrcamentoDto> findByOrcamento(OrcamentoConsultaDto dto);



}
