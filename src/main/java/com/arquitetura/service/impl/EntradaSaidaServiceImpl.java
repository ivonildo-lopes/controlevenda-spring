package com.arquitetura.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquitetura.DTO.ClienteDto;
import com.arquitetura.DTO.ComboEntradaSaidaDto;
import com.arquitetura.DTO.EntradaSaidaDto;
import com.arquitetura.DTO.VeiculoDto;
import com.arquitetura.dao.ClienteDao;
import com.arquitetura.dao.EntradaSaidaDao;
import com.arquitetura.dao.VeiculoDao;
import com.arquitetura.error.BadValueException;
import com.arquitetura.model.Cliente;
import com.arquitetura.model.EntradaSaida;
import com.arquitetura.model.Veiculo;
import com.arquitetura.repository.ClienteRepository;
import com.arquitetura.repository.VeiculoRepository;
import com.arquitetura.service.EntradaSaidaService;

@Service(value = "entradaSaidaService")
public class EntradaSaidaServiceImpl implements EntradaSaidaService {

	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private VeiculoDao veiculoDao;
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private EntradaSaidaDao dao;
	
	public ComboEntradaSaidaDto populaListas() {

		
		List<Veiculo> listVeiculos = this.veiculoDao.todosVeiculosNaLoja();
		List<Cliente> listClientes = this.clienteDao.findAll();
	
		ComboEntradaSaidaDto combos = new ComboEntradaSaidaDto();
		combos.setVeiculos(this.veiculoToVeiculoDto(listVeiculos));
		combos.setClientes(this.clienteToClienteDto(listClientes));
		
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
	
	private List<ClienteDto> clienteToClienteDto(List<Cliente> clientes) {
		
		List<ClienteDto> list = new ArrayList<ClienteDto>();
		
		clientes.stream().forEach(cliente -> {
			ClienteDto dto = new ClienteDto();
			dto.setId(cliente.getId());
			dto.setNome(cliente.getNome());
			dto.setCpf(cliente.getCpf());
			dto.setRg(cliente.getCpf());
			dto.setTelefone(cliente.getTelefone());
			dto.setEmail(cliente.getEmail());
			dto.setEndereco(cliente.getEndereco());
			dto.setCep(cliente.getCep());
			dto.setDataNascimento(cliente.getDataNascimento());
			
			list.add(dto);
		});
		
		return list;
	}

	@Override
	public EntradaSaidaDto save(EntradaSaidaDto dto) {
		EntradaSaida entity = new EntradaSaida();
		this.convertDtoToModel(entity, dto);

		this.veiculoRepository.atualizaVeiculoQuandoVendido(dto.getVeiculo().getId());
		
		if(Objects.isNull(this.dao.save(entity))) {
			throw new BadValueException("Não foi possivel realizar entrada saida");
		}
		
		return this.convertModelToDto(entity);
		
	}
	
	private void convertDtoToModel(EntradaSaida entity, EntradaSaidaDto dto) {
		entity.setData(dto.getData());
		entity.setTipo(dto.getTipo());
		entity.setValor(dto.getValor());
		
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
		
		
		Cliente cliente = new Cliente();
		cliente.setId(dto.getCliente().getId());
		cliente.setNome(dto.getCliente().getNome());
		cliente.setCpf(dto.getCliente().getCpf());
		cliente.setRg(dto.getCliente().getRg());
		cliente.setEmail(dto.getCliente().getEmail());
		cliente.setTelefone(dto.getCliente().getTelefone());
		cliente.setEndereco(dto.getCliente().getEndereco());
		cliente.setDataNascimento(dto.getCliente().getDataNascimento());
		cliente.setCep(dto.getCliente().getCep());
		entity.setCliente(cliente);
		
	}
	
	
	private EntradaSaidaDto convertModelToDto(EntradaSaida entity) {
		
		EntradaSaidaDto dto = new EntradaSaidaDto();
		
		dto.setData(entity.getData());
		dto.setTipo(entity.getTipo());
		dto.setValor(entity.getValor());
		
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
		
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setId(entity.getCliente().getId());
		clienteDto.setNome(entity.getCliente().getNome());
		clienteDto.setCpf(entity.getCliente().getCpf());
		clienteDto.setRg(entity.getCliente().getCpf());
		clienteDto.setTelefone(entity.getCliente().getTelefone());
		clienteDto.setEmail(entity.getCliente().getEmail());
		clienteDto.setEndereco(entity.getCliente().getEndereco());
		clienteDto.setCep(entity.getCliente().getCep());
		clienteDto.setDataNascimento(entity.getCliente().getDataNascimento());
		dto.setCliente(clienteDto);
		
		return dto;
	}
	
	


	
}
