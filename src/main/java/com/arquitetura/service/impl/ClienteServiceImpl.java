package com.arquitetura.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquitetura.DTO.CidadeDto;
import com.arquitetura.DTO.ClienteDto;
import com.arquitetura.DTO.EnderecoDto;
import com.arquitetura.DTO.EstadoDto;
import com.arquitetura.dao.ClienteDao;
import com.arquitetura.error.BadValueException;
import com.arquitetura.model.Cidade;
import com.arquitetura.model.Cliente;
import com.arquitetura.model.Endereco;
import com.arquitetura.model.Estado;
import com.arquitetura.service.ClienteService;

@Service(value = "clienteService")
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDao dao;
	
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
		cliente.setCpfOuCnpj(clienteDto.getCpfOuCnpj());
		cliente.setEmail(clienteDto.getEmail());
		cliente.setTelefones(clienteDto.getTelefones());
		cliente.setTipoCliente(clienteDto.getTipoCliente());
		
		cliente.setEnderecos(this.getEndereco(cliente,clienteDto.getEnderecos()));
		
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
		cliente.setCpfOuCnpj(clienteDto.getCpfOuCnpj());
		cliente.setEmail(clienteDto.getEmail());
		cliente.setTipoCliente(clienteDto.getTipoCliente());
		cliente.setTelefones(clienteDto.getTelefones());
		cliente.setEnderecos(this.getEndereco(cliente, clienteDto.getEnderecos()));
		
		this.dao.saveAndFlush(cliente);
		
		return this.convertModelToDto(cliente);
	}
	
	
	private List<Endereco> getEndereco(Cliente cliente,List<EnderecoDto> enderecosDto){
		List<Endereco> enderecos = new ArrayList<>();
		
		enderecosDto.stream().forEach(enderecoDto -> {
			Endereco endereco = new Endereco();
			endereco.setId(enderecoDto.getId());
			endereco.setCep(enderecoDto.getCep());
			endereco.setLogradouro(enderecoDto.getLogradouro());
			endereco.setNumero(enderecoDto.getNumero());
			endereco.setBairro(enderecoDto.getBairro());
			endereco.setComplemento(enderecoDto.getComplemento());
			endereco.setCidade(this.getCidade(enderecoDto.getCidade()));
			endereco.setCliente(cliente);
			
			enderecos.add(endereco);
		});
		
		return enderecos;
	}
	
	private List<EnderecoDto> getEnderecoDto(ClienteDto clienteDto, List<Endereco> enderecos){
		List<EnderecoDto> enderecosDto = new ArrayList<>();
		
		enderecos.stream().forEach(endereco -> {
			EnderecoDto enderecoDto = new EnderecoDto();
			enderecoDto.setId(endereco.getId());
			enderecoDto.setCep(endereco.getCep());
			enderecoDto.setLogradouro(endereco.getLogradouro());
			enderecoDto.setNumero(endereco.getNumero());
			enderecoDto.setBairro(endereco.getBairro());
			enderecoDto.setComplemento(endereco.getComplemento());
			enderecoDto.setCidade(this.getCidadesDto(endereco.getCidade()));
			
			//aqui
			enderecoDto.setCliente(clienteDto);
			
		});
		
		return enderecosDto;
		
	}
	
	
	private CidadeDto getCidadesDto(Cidade cidade){
		
		if(Objects.isNull(cidade)) {
			new BadValueException("Por favor informe a cidade");
		}
		
			CidadeDto cidadeDto = new CidadeDto();
			cidadeDto.setId(cidade.getId());
			cidadeDto.setNome(cidade.getNome());
			cidadeDto.setEstadoDto(this.getEstadoDto(cidade.getEstado()));
		
		
		return cidadeDto;
	}
	
	private Cidade getCidade(CidadeDto cidadeDto) {
		
		if(Objects.isNull(cidadeDto)) {
			new BadValueException("Por favor informe a cidade");
		}
		
		Cidade cidade = new Cidade();
		cidade.setId(cidadeDto.getId());
		cidade.setNome(cidadeDto.getNome());
		cidade.setEstado(this.getEstado(cidadeDto.getEstadoDto()));
		
		return cidade;
	}
	
	private ClienteDto convertModelToDto(Cliente cliente) {
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setId(cliente.getId());
		clienteDto.setNome(cliente.getNome());
		clienteDto.setCpfOuCnpj(cliente.getCpfOuCnpj());
		clienteDto.setTelefones(cliente.getTelefones());
		clienteDto.setEmail(cliente.getEmail());
		clienteDto.setTipoCliente(cliente.getTipoCliente());
		//aqui tbm
		clienteDto.setEnderecos(this.getEnderecoDto(clienteDto,cliente.getEnderecos()));
		
		return clienteDto;
	}
	
	private EstadoDto getEstadoDto(Estado estado) {
		
		if(Objects.isNull(estado)) {
			new BadValueException("Por favor informe o estado");
		}
		
		EstadoDto estadoDto = new EstadoDto();
		estadoDto.setId(estado.getId());
		estadoDto.setNome(estado.getNome());
		estadoDto.setSigla(estado.getSigla());
		
		return estadoDto;
	}
	
	private Estado getEstado(EstadoDto estadoDto) {
		
		if(Objects.isNull(estadoDto)) {
			new BadValueException("Por favor informe o estado");
		}
		
		Estado estado = new Estado();
		estado.setId(estadoDto.getId());
		estado.setNome(estadoDto.getNome());
		estado.setSigla(estadoDto.getSigla());
		
		return estado;
	}


	
}
