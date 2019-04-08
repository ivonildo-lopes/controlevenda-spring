package com.arquitetura.resource;

import io.swagger.annotations.ApiOperation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arquitetura.DTO.ComboEntradaSaidaDto;
import com.arquitetura.DTO.EntradaSaidaConsultaDto;
import com.arquitetura.DTO.EntradaSaidaDto;
import com.arquitetura.DTO.Response;
import com.arquitetura.service.EntradaSaidaService;

@RestController
@RequestMapping(value = "/entradaSaida")
public class EntradaSaidaResource implements Serializable {

	
	private static final long serialVersionUID = 5414438490471977726L;
	
	@Autowired
	private EntradaSaidaService service;

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
	@ApiOperation(value = "Adicionar cliente")
	public Response save(@RequestBody EntradaSaidaDto dto) {
		
		String mensagem = "";

		EntradaSaidaDto entradaSaida = this.service.save(dto);
		
		if(entradaSaida.getTipo().equals("1")){ mensagem += "Entrada de Veiculo realizada com sucesso";}
		if(entradaSaida.getTipo().equals("2")){ mensagem += "Saida de Veiculo realizada com sucesso";}

		return new Response().setData(entradaSaida).setInfos(mensagem);
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

		List<EntradaSaidaConsultaDto> list = this.service.findByEntradaSaida(dto);

		return new Response().setData(list).setInfos("Movimentações encontrada(s)");
	}
//	

}
