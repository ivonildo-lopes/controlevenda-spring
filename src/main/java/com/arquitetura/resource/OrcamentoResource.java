package com.arquitetura.resource;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arquitetura.DTO.ComboEntradaSaidaDto;
import com.arquitetura.DTO.OrcamentoConsultaDto;
import com.arquitetura.DTO.OrcamentoDto;
import com.arquitetura.DTO.Response;
import com.arquitetura.service.OrcamentoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/orcamento")
public class OrcamentoResource implements Serializable {

	
	private static final long serialVersionUID = 5414438490471977726L;
	
	@Autowired
	private OrcamentoService service;

	@RequestMapping(value = "/listas", method = RequestMethod.GET)
	@ApiOperation(value = "listar todas os clientes e os veiculos da loja")
	public Response findListas() {

		ComboEntradaSaidaDto combos = service.populaListas();

		return new Response().setData(combos);
	}
	
	@RequestMapping(value = "/listas/consulta", method = RequestMethod.GET)
	@ApiOperation(value = "listar todas os clientes e os veiculos")
	public Response findListasConsulta() {

		ComboEntradaSaidaDto combos = service.populaListasConsulta();

		return new Response().setData(combos);
	}

//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	@ApiOperation(value = "listar id por cliente")
//	public Response findById(@PathVariable Long id) {
//
//		Cliente cliente = service.findById(id);
//
//		return new Response().setData(cliente);
//	}
//
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Adicionar orcamento")
	public Response save(@RequestBody OrcamentoDto dto) {
		
		String mensagem = "";

		OrcamentoDto orcamento = this.service.save(dto);
		
		if(Objects.nonNull(orcamento)) {
			mensagem += "Orcamento salvo com sucesso";
		}
	

		return new Response().setData(orcamento).setInfos(mensagem);
	}
	
	@RequestMapping(value="/all", method = RequestMethod.POST)
	@ApiOperation(value = "Adicionar orcamentos")
	public Response saveAll(@RequestBody List<OrcamentoDto> dto) {
		
		String mensagem = "";

		try {
			this.service.saveAll(dto);
			mensagem += "Orcamento salvo com sucesso";
			
		} catch (Exception e) {
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
	public Response findByParams(OrcamentoConsultaDto dto) {

		List<OrcamentoDto> list = this.service.findByOrcamento(dto);

		return new Response().setData(list).setInfos("Movimentações encontrada(s)");
	}
//	

}
