package com.arquitetura.resource;

import io.swagger.annotations.ApiOperation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arquitetura.DTO.UsuarioDto;
import com.arquitetura.DTO.Response;
import com.arquitetura.model.Usuario;
import com.arquitetura.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource implements Serializable {

	private static final long serialVersionUID = -2200886726191335193L;

	@Autowired
	private UsuarioService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = "listar todas as usuarios")
	public Response findAll() {

		List<Usuario> usuarios = service.findAll();

		return new Response().setData(usuarios);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "listar id por usuario")
	public Response findById(@PathVariable Long id) {

		Usuario usuario = service.findById(id);

		return new Response().setData(usuario);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Adicionar usuario")
	public Response save(@RequestBody UsuarioDto usuarioDto) {

		UsuarioDto usuario = this.service.save(usuarioDto);

		return new Response().setData(usuario).setInfos("Usuario cadastrado com sucesso");
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	@ApiOperation(value = "alterar produto")
	public Response alterar(@PathVariable Long id,@RequestBody UsuarioDto usuarioDto) {

		UsuarioDto dto = this.service.alterar(id,usuarioDto);

		return new Response().setData(dto).setInfos("Usuario alterado com sucesso");
	}
	
	@RequestMapping(value="/params",method = RequestMethod.GET)
	@ApiOperation(value = "alterar usuario")
	public Response findByParams(UsuarioDto dto) {

		List<UsuarioDto> list = this.service.findByUsuarios(dto);

		return new Response().setData(this.service.findByUsuarios(dto)).setInfos("Usuario(s) encontrado(s)");
	}
	

}
