package com.arquitetura.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.arquitetura.DTO.VeiculoDto;
import com.arquitetura.dao.VeiculoDao;
import com.arquitetura.error.BadValueException;
import com.arquitetura.model.Veiculo;
import com.arquitetura.repository.CategoriaRepository;
import com.arquitetura.service.VeiculoService;

@Service(value = "veiculoService")
public class VeiculoServiceImpl implements VeiculoService {

	@Autowired
	private VeiculoDao dao;
	
	@Autowired
	private CategoriaRepository repository;

	public Veiculo findById(Long id) {

		if (Objects.isNull(id)) {
			throw new BadValueException("O ID precisa ser informado");
		}

		Veiculo obj = this.dao.findById(id).orElse(null);

		if (Objects.isNull(obj)) {
			throw new BadValueException("Não existe categoria com esse Id");
		}

		return obj;
	}
	
	@Override
	public List<Veiculo> findAll() {
		
		List<Veiculo> list = this.dao.findAll();
		
		if(Objects.isNull(list)) {
			throw new BadValueException("Não existe nenhum veiculo cadastrado");
		}
		
		return list;
	}
	
	@Override
	public List<VeiculoDto> findByIds(List ids) {
//		return this.repository.findByIds(ids);
		return null;
	}
	
	@Override
	public List<VeiculoDto> findByVeiculos(VeiculoDto dto) {
		return null;
	}

	@Override
	public VeiculoDto save(VeiculoDto dto) {

		Veiculo entity = new Veiculo();
		this.convertDtoToModel(entity, dto);
//		entity.setPlaca(dto.getPlaca());
//		entity.setRenavam(dto.getRenavam());
//		entity.setChassi(dto.getChassi());
//		entity.setModelo(dto.getModelo());
//		entity.setAnoModelo(dto.getAnoModelo());
//		entity.setAnoFabricacao(dto.getAnoFabricacao());
//		entity.setCor(dto.getCor());
//		entity.setObservacao(dto.getObservacao());
//		entity.setIsVendido(dto.getIsVendido());
		
		if(Objects.isNull(this.dao.save(entity))) {
			throw new BadValueException("Não foi possivel adicionar uma categoria");
		}
		
		return this.convertModelToDto(entity);
	}
	
	@Override
	public VeiculoDto update(Long id, VeiculoDto dto) {
		
		if(Objects.isNull(id)) {
			throw new BadValueException("Informe a categoria para ser alterada");
		}
		
		Veiculo entity = this.findById(id);
		this.convertDtoToModel(entity, dto);
//		entity.setPlaca(dto.getPlaca());
//		entity.setRenavam(dto.getRenavam());
//		entity.setChassi(dto.getChassi());
//		entity.setModelo(dto.getModelo());
//		entity.setAnoModelo(dto.getAnoModelo());
//		entity.setAnoFabricacao(dto.getAnoFabricacao());
//		entity.setCor(dto.getCor());
//		entity.setObservacao(dto.getObservacao());
//		entity.setIsVendido(dto.getIsVendido());
		
		if(Objects.isNull(this.dao.saveAndFlush(entity))) {
			throw new BadValueException("Não foi possivel alterar a categoria");
		}
		
		return this.convertModelToDto(entity);
	}
	
	private VeiculoDto convertModelToDto(Veiculo entity) {
		VeiculoDto dto = new VeiculoDto();
		dto.setId(entity.getId());
		dto.setPlaca(entity.getPlaca());
		dto.setRenavam(entity.getRenavam());
		dto.setChassi(entity.getChassi());
		dto.setModelo(entity.getModelo());
		dto.setAnoModelo(entity.getAnoModelo());
		dto.setAnoFabricacao(entity.getAnoFabricacao());
		dto.setCor(entity.getCor());
		dto.setObservacao(entity.getObservacao());
		dto.setIsVendido(entity.getIsVendido());
		
		return dto;
	}
	
	@Override
	public void delete(Long id) {
		
		Veiculo entity = this.findById(id);
		if(entity.getIsVendido()) {
			throw new BadValueException("Essa veiculo não pode ser excluido porque esta sendo vendido");
		}
		this.dao.delete(entity);
	}

	@Override
	public Page<Veiculo> findPage(Integer page, Integer linePerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		
		return dao.findAll(pageRequest);
	}
	
	private void convertDtoToModel(Veiculo entity, VeiculoDto dto) {
		entity.setPlaca(dto.getPlaca());
		entity.setRenavam(dto.getRenavam());
		entity.setChassi(dto.getChassi());
		entity.setModelo(dto.getModelo());
		entity.setAnoModelo(dto.getAnoModelo());
		entity.setAnoFabricacao(dto.getAnoFabricacao());
		entity.setCor(dto.getCor());
		entity.setObservacao(dto.getObservacao());
		entity.setIsVendido(dto.getIsVendido());
	}
}
