package com.arquitetura.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;

import com.arquitetura.DTO.VeiculoDto;
import com.arquitetura.model.Veiculo;

@NoRepositoryBean
public interface VeiculoService {
	
	Veiculo findById(Long id);

	VeiculoDto save(VeiculoDto dto);

	List<Veiculo> findAll();

	List<VeiculoDto> findByIds(List ids);
	
	List<VeiculoDto> findByVeiculos(VeiculoDto dto);

	VeiculoDto update(Long id, VeiculoDto dto);

	void delete(Long id);
	
	Page<Veiculo> findPage(Integer page, Integer linePerPage, String orderBy, String direction);

}
