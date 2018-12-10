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

import com.arquitetura.DTO.ProdutoDto;
import com.arquitetura.DTO.Response;
import com.arquitetura.model.Produto;
import com.arquitetura.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource implements Serializable {

	private static final long serialVersionUID = 9007056534492394991L;

	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = "listar todas os produtos")
	public Response findAll() {

		List<Produto> produtos = service.findAll();

		return new Response().setData(produtos);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "listar produto por id")
	public Response findById(@PathVariable Long id) {

		Produto produto = service.findById(id);

		return new Response().setData(produto);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Adicionar produto")
	public Response save(@RequestBody ProdutoDto produtoDto) {

		ProdutoDto produto = this.service.save(produtoDto);

		return new Response().setData(produto).setInfos("Produto Adicionado com sucesso");
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	@ApiOperation(value = "alterar produto")
	public Response alterar(@PathVariable Long id,@RequestBody ProdutoDto produtoDto) {

		ProdutoDto produto = this.service.alterar(id,produtoDto);

		return new Response().setData(produto).setInfos("Produto alterado com sucesso");
	}
	
	@RequestMapping(value="/saveAll",method = RequestMethod.POST)
	@ApiOperation(value = "Adicionar produto")
	public Response saveListProduto(@RequestBody List<ProdutoDto> produtosDto) {

		List<ProdutoDto> produtos = this.service.saveListProduto(produtosDto);

		return new Response().setData(produtos).setInfos("Produtos Adicionados com sucesso");
	}
}
