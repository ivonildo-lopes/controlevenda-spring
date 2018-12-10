package com.arquitetura.resource;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arquitetura.DTO.CidadeDto;
import com.arquitetura.DTO.Response;
import com.arquitetura.model.Cidade;
import com.arquitetura.service.CidadeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource implements Serializable {

	
	private static final long serialVersionUID = 9007056534492394991L;

	@Autowired
	private CidadeService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = "listar todas as cidades")
	public Response findAll() {

		List<Cidade> cidades = service.findAll();

		return new Response().setData(cidades);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "listar id por cidade")
	public Response findById(@PathVariable Long id) {

		Cidade cidade = service.findById(id);

		return new Response().setData(cidade);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Adicionar cidade")
	public Response save(@RequestBody CidadeDto cidadeDto) {

		CidadeDto cidade = this.service.save(cidadeDto);

		return new Response().setData(cidade).setInfos("Cidade Adiciona com sucesso");
	}
	
	
	@RequestMapping(value="/saveAll",method = RequestMethod.POST)
	@ApiOperation(value = "Adicionar cidade")
	public Response saveListCidade(@RequestBody List<CidadeDto> cidadesDto) {

		List<CidadeDto> cidades = this.service.saveListCidade(cidadesDto);

		return new Response().setData(cidades).setInfos("Cidade Adiciona com sucesso");
	}

}
