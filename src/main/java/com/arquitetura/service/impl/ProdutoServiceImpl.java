package com.arquitetura.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquitetura.DTO.CategoriaDto;
import com.arquitetura.DTO.ProdutoDto;
import com.arquitetura.dao.ProdutoDao;
import com.arquitetura.error.BadValueException;
import com.arquitetura.model.Categoria;
import com.arquitetura.model.Produto;
import com.arquitetura.service.CategoriaService;
import com.arquitetura.service.ProdutoService;

@Service(value = "produtoService")
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoDao dao;
	
	@Autowired
	private CategoriaService categoriaService;

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
		if(!Objects.isNull(produtoDto.getCategorias()) && produtoDto.getCategorias().size() > 0){
			List ids = produtoDto.getCategorias().stream().map(c -> c.getId()).collect(Collectors.toList());
			produto.setCategorias(this.getCategorias(this.categoriaService.findByIdCategorias(ids)));
		}
		
		if(Objects.isNull(this.dao.save(produto))) {
			throw new BadValueException("Não foi possivel adicionar uma produto");
		}
		
		return this.convertModelToDto(produto);
	}
	
	private List<Categoria> getCategorias(List<CategoriaDto> categoriasDto){
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		categoriasDto.stream().forEach(categoriaDto -> {
				Categoria categoria = new Categoria();
				
				categoria.setId(categoriaDto.getId());
				categoria.setNome(categoriaDto.getNome());
				
				categorias.add(categoria);
		});
		
		return categorias;
	}
	
	private List<CategoriaDto> getCategoriasDto(List<Categoria> categorias){
		List<CategoriaDto> categoriasDto = new ArrayList<CategoriaDto>();
		
		categorias.stream().forEach(categoria -> {
			CategoriaDto categoriaDto = new CategoriaDto();
			categoriaDto.setId(categoria.getId());
			categoriaDto.setNome(categoria.getNome());
			categoriasDto.add(categoriaDto);
		});
		
		return categoriasDto;
	}
	
	
	private ProdutoDto convertModelToDto(Produto produto) {
		ProdutoDto produtoDto = new ProdutoDto();
		produtoDto.setId(produto.getId());
		produtoDto.setNome(produto.getNome());
		produtoDto.setPreco(produto.getPreco());
		
		produtoDto.setCategorias(this.getCategoriasDto(produto.getCategorias()));
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
			
			if(!Objects.isNull(produtoDto.getCategorias()) && produtoDto.getCategorias().size() > 0){
				List ids = produtoDto.getCategorias().stream().map(c -> c.getId()).collect(Collectors.toList());
				produto.setCategorias(this.getCategorias(this.categoriaService.findByIdCategorias(ids)));
			}
		
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
			produtoDto.setCategorias(this.getCategoriasDto(produto.getCategorias()));
			
			produtosDto.add(produtoDto);
		});
		
		return produtosDto;
	}

}
