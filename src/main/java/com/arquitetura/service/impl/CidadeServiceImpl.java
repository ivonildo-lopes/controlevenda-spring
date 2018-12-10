package com.arquitetura.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquitetura.DTO.CidadeDto;
import com.arquitetura.DTO.EstadoDto;
import com.arquitetura.dao.CidadeDao;
import com.arquitetura.dao.EstadoDao;
import com.arquitetura.error.BadValueException;
import com.arquitetura.model.Cidade;
import com.arquitetura.model.Estado;
import com.arquitetura.service.CidadeService;

@Service(value = "cidadeService")
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeDao dao;

	@Autowired
	private EstadoDao estadoDao;
	
	public Cidade findById(Long id) {

		if (Objects.isNull(id)) {
			throw new BadValueException("O ID da cidade precisa ser informado");
		}

		Cidade obj = this.dao.findById(id).orElse(null);

		if (Objects.isNull(obj)) {
			throw new BadValueException("Não existe cidade com esse Id");
		}

		return obj;
	}

	@Override
	public CidadeDto save(CidadeDto cidadeDto) {

		Cidade cidade = new Cidade();
		cidade.setNome(cidadeDto.getNome());
		Estado estado = this.estadoDao.findByEstadoSigla(cidadeDto.getEstadoDto().getSigla());
		
		if(Objects.isNull(estado))
			cidade.setEstado(this.getEstado(cidadeDto.getEstadoDto()));
		else
			cidade.setEstado(estado);
		
		if(Objects.isNull(this.dao.save(cidade))) {
			throw new BadValueException("Não foi possivel adicionar uma cidade");
		}
		
		return this.convertModelToDto(cidade);
	}
	
	
	private CidadeDto convertModelToDto(Cidade cidade) {
		CidadeDto cidadeDto = new CidadeDto();
		cidadeDto.setId(cidade.getId());
		cidadeDto.setNome(cidade.getNome());
		cidadeDto.setEstadoDto(this.getEstadoDto(cidade.getEstado()));
		
		return cidadeDto;
	}
	
	private EstadoDto getEstadoDto(Estado estado){
		EstadoDto estadoDto = new EstadoDto();
		estadoDto.setId(estado.getId());
		estadoDto.setNome(estado.getNome());
		estadoDto.setSigla(estado.getSigla());
		return estadoDto;
	}
	
	private Estado getEstado(EstadoDto estadoDto){
		Estado estado = new Estado();
		estado.setNome(estadoDto.getNome());
		estado.setSigla(estadoDto.getSigla());
		return estado;
	}
	
	

	@Override
	public List<Cidade> findAll() {
		
		List<Cidade> cidades = this.dao.findAll();
		
		if(Objects.isNull(cidades)) {
			throw new BadValueException("Não existe nenhum cidade cadastrada");
		}

		return cidades;
	}

	@Override
	public List<CidadeDto> saveListCidade(List<CidadeDto> cidadesDto) {
		
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		cidadesDto.stream().forEach(cidadeDto -> {
			Cidade cidade = new Cidade();
			cidade.setNome(cidadeDto.getNome());
			cidade.setEstado(this.getEstado(cidadeDto.getEstadoDto()));
			cidades.add(cidade);
		});
		
		if(Objects.isNull(this.dao.saveAll(cidades))) {
			throw new BadValueException("Não foi possivel adicionar as cidades");
		}
		
		
		return this.getCidadesDto(cidades);
	}
		
	public List<Cidade> getCidades(List<CidadeDto> cidadesDto){
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		cidadesDto.stream().forEach(cidadeDto -> {
				Cidade cidade = new Cidade();
				
				cidade.setId(cidadeDto.getId());
				cidade.setNome(cidadeDto.getNome());
				cidade.setEstado(this.getEstado(cidadeDto.getEstadoDto()));
				
				cidades.add(cidade);
		});
		
		return cidades;
	}
	
	public List<CidadeDto> getCidadesDto(List<Cidade> cidades){
		List<CidadeDto> cidadesDto = new ArrayList<CidadeDto>();
		
		cidades.stream().forEach(cidade -> {
			CidadeDto cidadeDto = new CidadeDto();
			cidadeDto.setId(cidade.getId());
			cidadeDto.setNome(cidade.getNome());
			cidadeDto.setEstadoDto(this.getEstadoDto(cidade.getEstado()));
			
			cidadesDto.add(cidadeDto);
		});
		
		return cidadesDto;
	}

}
