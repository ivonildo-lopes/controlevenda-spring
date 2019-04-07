package com.arquitetura.resource;

import io.swagger.annotations.ApiOperation;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arquitetura.DTO.Response;
import com.arquitetura.DTO.VeiculoDto;
import com.arquitetura.model.Veiculo;
import com.arquitetura.service.VeiculoService;

@RestController
@RequestMapping(value = "/veiculo")
public class VeiculoResource implements Serializable {

	
	private static final long serialVersionUID = 4074092969892551547L;
	
	@Autowired
	private VeiculoService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = "listar todod os veiculos")
	public Response findAll() {

		List<Veiculo> list = service.findAll();

		return new Response().setData(list).setStatus(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ApiOperation(value = "listar todos os veiculos paginadas")
	public Response findPage(@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linePerPage", defaultValue = "24") Integer linePerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Veiculo> list = service.findPage(page, linePerPage, orderBy, direction);

		return new Response().setData(list).setStatus(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "listar veiculo por id")
	public Response findById(@PathVariable Long id) {

		Veiculo entity = service.findById(id);

		return new Response().setData(entity).setStatus(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Apagar um veiculo")
	public Response delete(@PathVariable Long id) {

		this.service.delete(id);

		return new Response().setInfos("veiculo deletade com sucesso").setStatus(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Adicionar veiculo")
	public Response save(@Valid @RequestBody VeiculoDto dto) {

		VeiculoDto entityDto = this.service.save(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/categorias/{id}").buildAndExpand(entityDto.getId()).toUri();

		return new Response().setData(entityDto).setInfos("Veiculo Cadastrado com sucesso").setStatus(HttpStatus.CREATED).setUri(uri);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	@ApiOperation(value = "alterar veiculo")
	public Response update(@PathVariable Long id,@Valid @RequestBody VeiculoDto dto) {

		VeiculoDto entityDto = this.service.update(id,dto);

		return new Response().setData(entityDto).setInfos("Veiculo alterado com sucesso");
	}
	
	@RequestMapping(value="/params",method = RequestMethod.GET)
	@ApiOperation(value = "alterar veiculo")
	public Response findByParams(VeiculoDto dto) {

		List<VeiculoDto> list = this.service.findByVeiculos(dto);

		return new Response().setData(this.service.findByVeiculos(dto)).setInfos("Veiculo(s) encontrado(s)");
	}

}
