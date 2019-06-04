package com.arquitetura.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquitetura.DTO.CategoriaDto;
import com.arquitetura.DTO.ComboEntradaSaidaDto;
import com.arquitetura.DTO.OrcamentoConsultaDto;
import com.arquitetura.DTO.OrcamentoDto;
import com.arquitetura.DTO.VeiculoDto;
import com.arquitetura.dao.OrcametoDao;
import com.arquitetura.dao.VeiculoDao;
import com.arquitetura.error.BadValueException;
import com.arquitetura.model.Categoria;
import com.arquitetura.model.Orcamento;
import com.arquitetura.model.Veiculo;
import com.arquitetura.repository.OrcamentoRepository;
import com.arquitetura.repository.VeiculoRepository;
import com.arquitetura.service.OrcamentoService;

@Service(value = "orcamentoService")
public class OrcamentoServiceImpl implements OrcamentoService {

	@Autowired
	private VeiculoDao veiculoDao;
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private OrcamentoRepository repository;
	
	@Autowired
	private OrcametoDao dao;
	
	
	public ComboEntradaSaidaDto populaListas() {

		
		List<Veiculo> listVeiculos = this.veiculoDao.todosVeiculosNaLoja();
	
		ComboEntradaSaidaDto combos = new ComboEntradaSaidaDto();
		combos.setVeiculos(this.veiculoToVeiculoDto(listVeiculos));
		
		return combos;
	}
	
	public ComboEntradaSaidaDto populaListasConsulta() {
	
			
			List<Veiculo> listVeiculos = this.veiculoDao.findAll();
		
			ComboEntradaSaidaDto combos = new ComboEntradaSaidaDto();
			combos.setVeiculos(this.veiculoToVeiculoDto(listVeiculos));
			
			return combos;
		}
	
	private List<VeiculoDto> veiculoToVeiculoDto(List<Veiculo> veiculos) {
		
		List<VeiculoDto> list = new ArrayList<VeiculoDto>();
		veiculos.stream().forEach(veiculo -> {
			VeiculoDto dto = new VeiculoDto();
			dto.setId(veiculo.getId());
			dto.setPlaca(veiculo.getPlaca());
			dto.setRenavam(veiculo.getRenavam());
			dto.setChassi(veiculo.getChassi());
			dto.setModelo(veiculo.getModelo());
			dto.setAnoModelo(veiculo.getAnoModelo());
			dto.setAnoFabricacao(veiculo.getAnoFabricacao());
			dto.setCor(veiculo.getCor());
			dto.setObservacao(veiculo.getObservacao());
			dto.setIsVendido(veiculo.getIsVendido());
			list.add(dto);
		});
		
		return list;
	}
	

	@Override
	public OrcamentoDto save(OrcamentoDto dto) {
		
		if(dto == null) {
			throw new BadValueException("Veiculo ja foi dado Entrada");
		}
		
		
		Orcamento entity = new Orcamento();

		this.convertDtoToModel(entity, dto);

		if(Objects.isNull(this.dao.save(entity))) {
			throw new BadValueException("Não foi possivel realizar entrada saida");
		}
		
		return this.convertModelToDto(entity);
		
	}
	
	@Override
	public void saveAll(List<OrcamentoDto> orcamentos) {
		
		List<Orcamento> orcamento = new ArrayList<Orcamento>();
		
		orcamentos.stream().forEach(orcamentoDto -> {
			Orcamento orc = new Orcamento();
//			orc.setData(orcamentoDto.getData());
//			orc.setDescricao(orcamentoDto.getDescricao());
//			orc.setValor(orcamentoDto.getValor());
			this.convertDtoToModel(orc, orcamentoDto);
			orcamento.add(orc);
		});
		
		if(Objects.isNull(this.dao.saveAll(orcamento))) {
			throw new BadValueException("Não foi possivel adicionar os orcamentos");
		}
		
		
	}
	
	private void convertDtoToModel(Orcamento entity, OrcamentoDto dto) {
		entity.setData(dto.getData());
		entity.setValor(dto.getValor());
		entity.setDescricao(dto.getDescricao());
		
		Veiculo veiculo = new Veiculo();
		veiculo.setId(dto.getVeiculo().getId());
		veiculo.setPlaca(dto.getVeiculo().getPlaca());
		veiculo.setRenavam(dto.getVeiculo().getRenavam());
		veiculo.setChassi(dto.getVeiculo().getChassi());
		veiculo.setModelo(dto.getVeiculo().getModelo());
		veiculo.setAnoModelo(dto.getVeiculo().getAnoModelo());
		veiculo.setAnoFabricacao(dto.getVeiculo().getAnoFabricacao());
		veiculo.setCor(dto.getVeiculo().getCor());
		veiculo.setObservacao(dto.getVeiculo().getObservacao());
		veiculo.setIsVendido(dto.getVeiculo().getIsVendido());
		
		entity.setVeiculo(veiculo);
		
	}
	
	
	private OrcamentoDto convertModelToDto(Orcamento entity) {
		
		OrcamentoDto dto = new OrcamentoDto();
		
		dto.setData(entity.getData());
		dto.setValor(entity.getValor());
		dto.setDescricao(entity.getDescricao());
		
		VeiculoDto veiculoDto = new VeiculoDto();
		veiculoDto.setId(entity.getVeiculo().getId());
		veiculoDto.setPlaca(entity.getVeiculo().getPlaca());
		veiculoDto.setRenavam(entity.getVeiculo().getRenavam());
		veiculoDto.setChassi(entity.getVeiculo().getChassi());
		veiculoDto.setModelo(entity.getVeiculo().getModelo());
		veiculoDto.setAnoModelo(entity.getVeiculo().getAnoModelo());
		veiculoDto.setAnoFabricacao(entity.getVeiculo().getAnoFabricacao());
		veiculoDto.setCor(entity.getVeiculo().getCor());
		veiculoDto.setObservacao(entity.getVeiculo().getObservacao());
		veiculoDto.setIsVendido(entity.getVeiculo().getIsVendido());
		
		dto.setVeiculo(veiculoDto);
		
		return dto;
	}
	

	@Override
	public List<OrcamentoDto> findByOrcamento(OrcamentoConsultaDto dto) {
		return this.repository.findByOrcamento(dto);
	}

	


	
}
