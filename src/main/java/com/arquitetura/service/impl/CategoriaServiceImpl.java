package com.arquitetura.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.arquitetura.DTO.CategoriaDto;
import com.arquitetura.dao.CategoriaDao;
import com.arquitetura.error.BadValueException;
import com.arquitetura.model.Categoria;
import com.arquitetura.repository.CategoriaRepository;
import com.arquitetura.service.CategoriaService;

@Service(value = "categoriaService")
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaDao dao;
	
	@Autowired
	private CategoriaRepository repository;

	public Categoria findById(Long id) {

		if (Objects.isNull(id)) {
			throw new BadValueException("O ID da categoria precisa ser informado");
		}

		Categoria obj = this.dao.findById(id).orElse(null);

		if (Objects.isNull(obj)) {
			throw new BadValueException("Não existe categoria com esse Id");
		}

		return obj;
	}
	
	@Override
	public List<Categoria> findAll() {
		
		List<Categoria> categorias = this.dao.findAll();
		
		if(Objects.isNull(categorias)) {
			throw new BadValueException("Não existe nenhum categoria cadastrada");
		}

		return categorias;
	}
	
	@Override
	public List<CategoriaDto> findByIdCategorias(List ids) {
		return this.repository.findByIdCategorias(ids);
	}

	@Override
	public CategoriaDto save(CategoriaDto categoriaDto) {

		Categoria categoria = new Categoria();
		categoria.setNome(categoriaDto.getNome());
		
		if(Objects.isNull(this.dao.save(categoria))) {
			throw new BadValueException("Não foi possivel adicionar uma categoria");
		}
		
		return this.convertModelToDto(categoria);
	}
	
	@Override
	public CategoriaDto update(Long id, CategoriaDto categoriaDto) {
		
		if(Objects.isNull(id)) {
			throw new BadValueException("Informe a categoria para ser alterada");
		}
		
		Categoria categoria = this.findById(id);
		categoria.setNome(categoriaDto.getNome());
		
		if(Objects.isNull(this.dao.saveAndFlush(categoria))) {
			throw new BadValueException("Não foi possivel alterar a categoria");
		}
		
		return this.convertModelToDto(categoria);
	}
	
	
	private CategoriaDto convertModelToDto(Categoria categoria) {
		CategoriaDto categoriaDto = new CategoriaDto();
		categoriaDto.setId(categoria.getId());
		categoriaDto.setNome(categoria.getNome());
		
		return categoriaDto;
	}
	
	@Override
	public List<CategoriaDto> saveListCategoria(List<CategoriaDto> categoriasDto) {
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		categoriasDto.stream().forEach(categoriaDto -> {
			Categoria categoria = new Categoria();
			categoria.setNome(categoriaDto.getNome());
			categorias.add(categoria);
		});
		
		if(Objects.isNull(this.dao.saveAll(categorias))) {
			throw new BadValueException("Não foi possivel adicionar as categorias");
		}
		
		
		return this.getCategoriasDto(categorias);
	}
		
	public List<Categoria> getCategorias(List<CategoriaDto> categoriasDto){
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		categoriasDto.stream().forEach(categoriaDto -> {
				Categoria categoria = new Categoria();
				
				categoria.setId(categoriaDto.getId());
				categoria.setNome(categoriaDto.getNome());
				
				categorias.add(categoria);
		});
		
		return categorias;
	}
	
	public List<CategoriaDto> getCategoriasDto(List<Categoria> categorias){
		List<CategoriaDto> categoriasDto = new ArrayList<CategoriaDto>();
		
		categorias.stream().forEach(categoria -> {
			CategoriaDto categoriaDto = new CategoriaDto();
			categoriaDto.setId(categoria.getId());
			categoriaDto.setNome(categoria.getNome());
			categoriasDto.add(categoriaDto);
		});
		
		return categoriasDto;
	}

	@Override
	public void delete(Long id) {
		
		Categoria categoria = this.findById(id);
		
		if(categoria.getProdutos().size() > 0) {
			throw new BadValueException("Essa categoria não pode ser excluida porque esta sendo associada a alguns produtos");
		}
		
		this.dao.delete(categoria);
		
	}

	@Override
	public Page<Categoria> findPage(Integer page, Integer linePerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		
		return dao.findAll(pageRequest);
	}



}
