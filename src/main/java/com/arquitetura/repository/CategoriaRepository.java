package com.arquitetura.repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arquitetura.DTO.CategoriaDto;

@Component
public class CategoriaRepository implements Serializable{

	private static final long serialVersionUID = 7640048087488349822L;

	
	private final transient SqlSession sqlSession;
	
	@Autowired
	public CategoriaRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	public List<CategoriaDto> listaCategorias(){
		return this.sqlSession.selectList("CategoriaRepository.getCategorias");
	}


	public List<CategoriaDto> findByIdCategorias(List ids) {
		Map filter = new HashMap<>();
		filter.put("ids", ids);
		return this.sqlSession.selectList("CategoriaRepository.getCategoriasByIdCategorias",filter);
	}
	
	
}
