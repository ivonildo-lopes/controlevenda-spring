package com.arquitetura.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquitetura.DTO.ProdutoDto;
import com.arquitetura.dao.ProdutoDao;
import com.arquitetura.error.BadValueException;
import com.arquitetura.model.Produto;
import com.arquitetura.service.ProdutoService;

@Service(value = "produtoService")
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoDao dao;
	

	public Produto findById(Long id) {

		if (Objects.isNull(id)) {
			throw new BadValueException("O ID da produto precisa ser informado");
		}

		Produto obj = this.dao.findById(id).orElse(null);

		if (Objects.isNull(obj)) {
			throw new BadValueException("Não existe produto com esse Id");
		}

		return obj;
	}

	@Override
	public ProdutoDto save(ProdutoDto produtoDto) {
		
		if(Objects.isNull(produtoDto)) {
			throw new BadValueException("Informe os dados do Produto");
		}

		Produto produto = new Produto();
		produto.setNome(produtoDto.getNome());
		produto.setPreco(produtoDto.getPreco());
		
		if(Objects.isNull(this.dao.save(produto))) {
			throw new BadValueException("Não foi possivel adicionar uma produto");
		}
		
		return this.convertModelToDto(produto);
	}
	
	
	private ProdutoDto convertModelToDto(Produto produto) {
		ProdutoDto produtoDto = new ProdutoDto();
		produtoDto.setId(produto.getId());
		produtoDto.setNome(produto.getNome());
		produtoDto.setPreco(produto.getPreco());
		
		return produtoDto;
	}

	@Override
	public List<Produto> findAll() {
		
		List<Produto> produtos = this.dao.findAll();
		
		if(Objects.isNull(produtos)) {
			throw new BadValueException("Não existe nenhum produto cadastrada");
		}

		return produtos;
	}

	@Override
	public List<ProdutoDto> saveListProduto(List<ProdutoDto> produtosDto) {
		
		if(Objects.isNull(produtosDto)) {
			throw new BadValueException("Informe os dados dod Produtos");
		}
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		produtosDto.stream().forEach(produtoDto -> {
			Produto produto = new Produto();
			produto.setNome(produtoDto.getNome());
			produto.setPreco(produtoDto.getPreco());
			produtos.add(produto);
		});
		
		if(Objects.isNull(this.dao.saveAll(produtos))) {
			throw new BadValueException("Não foi possivel adicionar as produtos");
		}
		
		
		return this.convertListModelToDto(produtos);
	}
	
	private List<ProdutoDto> convertListModelToDto(List<Produto> produtos) {
		List<ProdutoDto> produtosDto = new ArrayList<>();
		
		produtos.stream().forEach(produto -> {
			ProdutoDto produtoDto = new ProdutoDto();
			produtoDto.setId(produto.getId());
			produtoDto.setNome(produto.getNome());
			produtoDto.setPreco(produto.getPreco());
			
			produtosDto.add(produtoDto);
		});
		
		return produtosDto;
	}

}
