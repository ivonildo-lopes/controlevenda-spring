package com.arquitetura.repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arquitetura.DTO.ProdutoDto;

@Component
public class ProdutoRepository implements Serializable{

	
	private static final long serialVersionUID = -5581605844727345004L;
	
	private final transient SqlSession sqlSession;
	
	@Autowired
	public ProdutoRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	public List<ProdutoDto> findProdutosByIdCategoria(Long idCategoria){
		
		Map filter = new HashMap<>();
		filter.put("idCategoria", idCategoria);
		
		return this.sqlSession.selectList("ProdutoRepository.getProdutosIdCategoria", filter);
	}
	
	
}
