package com.arquitetura.resource;

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

import com.arquitetura.DTO.CategoriaDto;
import com.arquitetura.DTO.ProdutoDto;
import com.arquitetura.DTO.Response;
import com.arquitetura.model.Categoria;
import com.arquitetura.service.CategoriaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource implements Serializable {

	
	private static final long serialVersionUID = 9007056534492394991L;

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = "listar todas as categorias")
	public Response findAll() {

		List<Categoria> categorias = service.findAll();

		return new Response().setData(categorias).setStatus(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ApiOperation(value = "listar todas as categorias paginadas")
	public Response findPage(@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linePerPage", defaultValue = "24") Integer linePerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Categoria> categorias = service.findPage(page, linePerPage, orderBy, direction);

		return new Response().setData(categorias).setStatus(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "listar id por categoria")
	public Response findById(@PathVariable Long id) {

		Categoria categoria = service.findById(id);

		return new Response().setData(categoria).setStatus(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Apagar uma categoria")
	public Response delete(@PathVariable Long id) {

		this.service.delete(id);

		return new Response().setInfos("categoria deletada").setStatus(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Adicionar categoria")
	public Response save(@Valid @RequestBody CategoriaDto categoriaDto) {

		CategoriaDto categoria = this.service.save(categoriaDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();

		return new Response().setData(categoria).setInfos("Categoria Adiciona com sucesso").setStatus(HttpStatus.CREATED).setUri(uri);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	@ApiOperation(value = "alterar categoria")
	public Response update(@PathVariable Long id,@Valid @RequestBody CategoriaDto categoriaDto) {

		CategoriaDto dto = this.service.update(id,categoriaDto);

		return new Response().setData(dto).setInfos("Categoria alterado com sucesso");
	}
	
	
	@RequestMapping(value="/saveAll",method = RequestMethod.POST)
	@ApiOperation(value = "Adicionar varias categorias")
	public Response saveListCategoria(@RequestBody List<CategoriaDto> categoriasDto) {

		List<CategoriaDto> categorias = this.service.saveListCategoria(categoriasDto);

		return new Response().setData(categorias).setInfos("Categoria Adiciona com sucesso").setStatus(HttpStatus.CREATED);
	}

}
