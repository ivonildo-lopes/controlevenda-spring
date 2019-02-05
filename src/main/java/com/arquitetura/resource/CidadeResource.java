package com.arquitetura.resource;

import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arquitetura.DTO.CidadeDto;
import com.arquitetura.DTO.Response;
import com.arquitetura.model.Cidade;
import com.arquitetura.service.CidadeService;
import com.arquitetura.util.GeradorRelatorio;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource implements Serializable {

	
	private static final long serialVersionUID = 9007056534492394991L;

	@Autowired
	private CidadeService service;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ApiOperation(value = "teste")
	public Response imprime(HttpServletResponse reponse) throws JRException, IOException {

		List<Cidade> cidades = service.findAll();
//		parametros = parametros == null ? parametros = new HashMap<>() : parametros;
//		
//		InputStream jasperStream = this.getClass().getResourceAsStream("/rel/teste.jasper");
//		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//		
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parametros,new JRBeanCollectionDataSource(cidades));
//		reponse.setContentType("application/pdf");
//		reponse.setHeader("Content-Disposition", "inline; filename=teste.pdf");
		byte[] retorno = GeradorRelatorio.gerarRelatorio(cidades, "teste.pdf");

//		byte[] retorno = JasperExportManager.exportReportToPdf(jasperPrint);
		return new Response().setData(retorno);
	}
	
	
	@SuppressWarnings("rawtypes")
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
