package com.arquitetura.service;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.arquitetura.DTO.CidadeDto;
import com.arquitetura.model.Cidade;

@NoRepositoryBean
public interface CidadeService {
	
	Cidade findById(Long id);

	CidadeDto save(CidadeDto cidadeDto);

	List<Cidade> findAll();

	List<CidadeDto> saveListCidade(List<CidadeDto> cidadeDto);

	List<CidadeDto> getCidadesDto(List<Cidade> cidades);

	List<Cidade> getCidades(List<CidadeDto> findByIdCidades);

}
