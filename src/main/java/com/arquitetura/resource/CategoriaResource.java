package com.arquitetura.resource;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arquitetura.DTO.Response;
import com.arquitetura.model.Categoria;
import com.arquitetura.service.CategoriaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
public class CategoriaResource implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9007056534492394991L;
	@Autowired
	private CategoriaService service;

//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseDto listar() {
//		
//		
//		Categoria cat3 = service.findById(3L);
//				
//		
//		return new ResponseDto("Rest está funcionando",true,cat3);
//		
//	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "listar id por categoria")
	public Response listar() {
		
		
		Categoria cat3 = service.findById(3L);
				
		
		return new Response().setData(cat3);
		
	}
}
