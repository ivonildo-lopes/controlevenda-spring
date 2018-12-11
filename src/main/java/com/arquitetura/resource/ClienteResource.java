package com.arquitetura.resource;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arquitetura.DTO.ClienteDto;
import com.arquitetura.DTO.ProdutoDto;
import com.arquitetura.DTO.Response;
import com.arquitetura.model.Cliente;
import com.arquitetura.service.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource implements Serializable {

	
	private static final long serialVersionUID = 9007056534492394991L;

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = "listar todas as clientes")
	public Response findAll() {

		List<Cliente> clientes = service.findAll();

		return new Response().setData(clientes);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "listar id por cliente")
	public Response findById(@PathVariable Long id) {

		Cliente cliente = service.findById(id);

		return new Response().setData(cliente);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Adicionar cliente")
	public Response save(@RequestBody ClienteDto clienteDto) {

		ClienteDto cliente = this.service.save(clienteDto);

		return new Response().setData(cliente).setInfos("Cliente Adiciona com sucesso");
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	@ApiOperation(value = "alterar produto")
	public Response alterar(@PathVariable Long id,@RequestBody ClienteDto clienteDto) {

		ClienteDto dto = this.service.alterar(id,clienteDto);

		return new Response().setData(dto).setInfos("Cliente alterado com sucesso");
	}
	

}
