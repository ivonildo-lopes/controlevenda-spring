package com.arquitetura.service;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.arquitetura.DTO.ClienteDto;
import com.arquitetura.model.Cliente;

@NoRepositoryBean
public interface ClienteService {
	
	Cliente findById(Long id);

	ClienteDto save(ClienteDto clienteDto);

	List<Cliente> findAll();
	
	ClienteDto alterar(Long id, ClienteDto clienteDto);



}
