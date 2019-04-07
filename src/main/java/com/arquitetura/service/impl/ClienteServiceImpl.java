package com.arquitetura.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquitetura.DTO.ClienteDto;
import com.arquitetura.dao.ClienteDao;
import com.arquitetura.error.BadValueException;
import com.arquitetura.model.Cliente;
import com.arquitetura.repository.ClienteRepository;
import com.arquitetura.service.ClienteService;

@Service(value = "clienteService")
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDao dao;
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente findById(Long id) {

		if (Objects.isNull(id)) {
			throw new BadValueException("O ID da cliente precisa ser informado");
		}

		Cliente obj = this.dao.findById(id).orElse(null);

		if (Objects.isNull(obj)) {
			throw new BadValueException("Não existe cliente com esse Id");
		}

		return obj;
	}
	
	@Override
	public List<Cliente> findAll() {
		
		List<Cliente> clientes = this.dao.findAll();
		
		if(Objects.isNull(clientes)) {
			throw new BadValueException("Não existe nenhum cliente cadastrada");
		}

		return clientes;
	}

	@Override
	public ClienteDto save(ClienteDto clienteDto) {
		
		if(Objects.isNull(clienteDto)) {
			throw new BadValueException("Informe os dados do cliente");
		}

		Cliente cliente = new Cliente();
		cliente.setNome(clienteDto.getNome());
		cliente.setCpf(clienteDto.getCpf());
		cliente.setRg(clienteDto.getRg());
		cliente.setEmail(clienteDto.getEmail());
		cliente.setTelefone(clienteDto.getTelefone());
		cliente.setEndereco(clienteDto.getEndereco());
		
		cliente.setDataNascimento(clienteDto.getDataNascimento());
		cliente.setCep(clienteDto.getCep());
		
		if(Objects.isNull(this.dao.save(cliente))) {
			throw new BadValueException("Não foi possivel adicionar um cliente");
		}
		
		return this.convertModelToDto(cliente);
	}
	
	@Override
	public ClienteDto alterar(Long id, ClienteDto clienteDto) {
		
		if (Objects.isNull(id)) {
			throw new BadValueException("Informe o cliente para ser alterado");
		}
		
		Cliente cliente = this.findById(id);
		cliente.setNome(clienteDto.getNome());
		cliente.setCpf(clienteDto.getCpf());
		cliente.setRg(clienteDto.getRg());
		cliente.setEmail(clienteDto.getEmail());
		cliente.setTelefone(clienteDto.getTelefone());
		cliente.setEndereco(clienteDto.getEndereco());
		
		cliente.setDataNascimento(clienteDto.getDataNascimento());
		cliente.setCep(clienteDto.getCep());
		
		this.dao.saveAndFlush(cliente);
		
		return this.convertModelToDto(cliente);
	}
	
	
		
	private ClienteDto convertModelToDto(Cliente cliente) {
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setId(cliente.getId());
		clienteDto.setNome(cliente.getNome());
		clienteDto.setCpf(cliente.getCpf());
		clienteDto.setRg(cliente.getCpf());
		clienteDto.setTelefone(cliente.getTelefone());
		clienteDto.setEmail(cliente.getEmail());
		//aqui tbm
		clienteDto.setEndereco(cliente.getEndereco());
		clienteDto.setCep(cliente.getCep());
		clienteDto.setDataNascimento(cliente.getDataNascimento());
		return clienteDto;
	}
	
	

	@Override
	public List<ClienteDto> findByClientes(ClienteDto dto) {
		return this.repository.findByClientes(dto);
	}


	
}
