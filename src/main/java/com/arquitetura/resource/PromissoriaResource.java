package com.arquitetura.resource;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arquitetura.DTO.EntradaSaidaDto;
import com.arquitetura.DTO.PromissoriaDto;
import com.arquitetura.DTO.Response;
import com.arquitetura.service.PromissoriaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/promissoria")
public class PromissoriaResource implements Serializable {

	
	
	private static final long serialVersionUID = -7166293172874037204L;
	
	@Autowired
	private PromissoriaService service;

	@RequestMapping(value = "/listas", method = RequestMethod.GET)
	@ApiOperation(value = "listar todas os clientes e os veiculos da loja")
	public Response findListas() {

//		ComboEntradaSaidaDto combos = service.populaListas();

		return new Response().setData(null);
	}
	
	@RequestMapping(value = "/listas/consulta", method = RequestMethod.GET)
	@ApiOperation(value = "listar todas os clientes e os veiculos")
	public Response findListasConsulta() {

//		ComboEntradaSaidaDto combos = service.populaListasConsulta();

		return new Response().setData(null);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Adicionar promissoria")
	public Response save(@RequestBody PromissoriaDto dto) {
		
		String mensagem = "";

		try {
			this.service.save(dto);
			mensagem += "Promissoria(s) gerada com sucesso";
		} catch (Exception e) {
			// TODO: handle exception
		}

		return new Response().setData(null).setInfos(mensagem);
	}
//	
//	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
//	@ApiOperation(value = "alterar produto")
//	public Response alterar(@PathVariable Long id,@RequestBody ClienteDto clienteDto) {
//
//		ClienteDto dto = this.service.alterar(id,clienteDto);
//
//		return new Response().setData(dto).setInfos("Cliente alterado com sucesso");
//	}
//	
	@RequestMapping(value="/params",method = RequestMethod.GET)
	@ApiOperation(value = "consultar entradas por params")
	public Response findByParams(EntradaSaidaDto dto) {

//		List<EntradaSaidaConsultaDto> list = this.service.findByEntradaSaida(dto);

		return new Response().setData(null).setInfos("Movimentações encontrada(s)");
	}
//	

}
