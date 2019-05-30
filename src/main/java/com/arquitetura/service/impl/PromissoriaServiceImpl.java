package com.arquitetura.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquitetura.DTO.ClienteDto;
import com.arquitetura.DTO.EntradaSaidaDto;
import com.arquitetura.DTO.PromissoriaConsultaDto;
import com.arquitetura.DTO.PromissoriaDto;
import com.arquitetura.DTO.VeiculoDto;
import com.arquitetura.dao.ClienteDao;
import com.arquitetura.dao.PromissoriaDao;
import com.arquitetura.dao.VeiculoDao;
import com.arquitetura.model.Cliente;
import com.arquitetura.model.EntradaSaida;
import com.arquitetura.model.Promissoria;
import com.arquitetura.model.Veiculo;
import com.arquitetura.repository.ClienteRepository;
import com.arquitetura.repository.EntradaSaidaRepository;
import com.arquitetura.repository.PromissoriaRepository;
import com.arquitetura.repository.VeiculoRepository;
import com.arquitetura.service.PromissoriaService;

@Service(value = "promissoriaService")
public class PromissoriaServiceImpl implements PromissoriaService {

	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private PromissoriaDao dao;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PromissoriaRepository repository;
	
//	public ComboEntradaSaidaDto populaListas() {
//
//		
//		List<Veiculo> listVeiculos = this.veiculoDao.todosVeiculosNaLoja();
//		List<Cliente> listClientes = this.clienteDao.findAll();
//	
//		ComboEntradaSaidaDto combos = new ComboEntradaSaidaDto();
//		combos.setVeiculos(this.veiculoToVeiculoDto(listVeiculos));
//		combos.setClientes(this.clienteToClienteDto(listClientes));
//		
//		return combos;
//	}
	
//	public ComboEntradaSaidaDto populaListasConsulta() {
//	
//			
//			List<Veiculo> listVeiculos = this.veiculoDao.findAll();
//			List<Cliente> listClientes = this.clienteDao.findAll();
//		
//			ComboEntradaSaidaDto combos = new ComboEntradaSaidaDto();
//			combos.setVeiculos(this.veiculoToVeiculoDto(listVeiculos));
//			combos.setClientes(this.clienteToClienteDto(listClientes));
//			
//			return combos;
//		}
	
//	private List<VeiculoDto> veiculoToVeiculoDto(List<Veiculo> veiculos) {
//		
//		List<VeiculoDto> list = new ArrayList<VeiculoDto>();
//		veiculos.stream().forEach(veiculo -> {
//			VeiculoDto dto = new VeiculoDto();
//			dto.setId(veiculo.getId());
//			dto.setPlaca(veiculo.getPlaca());
//			dto.setRenavam(veiculo.getRenavam());
//			dto.setChassi(veiculo.getChassi());
//			dto.setModelo(veiculo.getModelo());
//			dto.setAnoModelo(veiculo.getAnoModelo());
//			dto.setAnoFabricacao(veiculo.getAnoFabricacao());
//			dto.setCor(veiculo.getCor());
//			dto.setObservacao(veiculo.getObservacao());
//			dto.setIsVendido(veiculo.getIsVendido());
//			list.add(dto);
//		});
//		
//		return list;
//	}
	
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
	public void save(PromissoriaDto dto) {
		
		int qtdParcela = dto.getQtdParcela();
		
		for (int i = 1; i < dto.getQtdParcela()+1; i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dto.getData()); // Objeto Date() do usuário
			cal.add(cal.MONTH, +i);
			
			Promissoria promissoria = new Promissoria();
			promissoria.setCliente(this.convertClienteDtoToModel(dto.getCliente()));
			promissoria.setData(cal.getTime());
			promissoria.setValor(dto.getValor()/qtdParcela);
			promissoria.setIsPago(false);
			promissoria.setDescricao(i + "º parcela de " + qtdParcela);
			
			this.dao.save(promissoria);
		}
		
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
	
	
	private Cliente convertClienteDtoToModel(ClienteDto dto) {
		
		Cliente cliente = new Cliente();
		cliente.setId(dto.getId());
		cliente.setNome(dto.getNome());
		cliente.setCpf(dto.getCpf());
		cliente.setRg(dto.getRg());
		cliente.setEmail(dto.getEmail());
		cliente.setTelefone(dto.getTelefone());
		cliente.setEndereco(dto.getEndereco());
		cliente.setDataNascimento(dto.getDataNascimento());
		cliente.setCep(dto.getCep());

		return cliente;
	}
	
	
	private EntradaSaidaDto convertModelToDto(EntradaSaida entity) {
		
		EntradaSaidaDto dto = new EntradaSaidaDto();
		dto.setData(entity.getData());
		dto.setTipo(entity.getTipo());
		dto.setValor(entity.getValor());
		
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
	

	@Override
	public List<PromissoriaDto> findByPromissoria(PromissoriaConsultaDto dto) {
		return this.repository.findByPromissoria(dto);
	}
	
	


	
}
