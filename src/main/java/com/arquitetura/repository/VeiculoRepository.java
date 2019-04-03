package com.arquitetura.repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arquitetura.DTO.CategoriaDto;
import com.arquitetura.DTO.VeiculoDto;

@Component
public class VeiculoRepository implements Serializable{

	private static final long serialVersionUID = -2419087739281126479L;
	
	private final transient SqlSession sqlSession;
	
	@Autowired
	public VeiculoRepository(SqlSession sqlSession) {
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
	
	
	public List<VeiculoDto> findByVeiculos(VeiculoDto dto ) {
		Map<String, Object> filter = new HashMap<>();
		filter.put("placa", dto.getPlaca());
		filter.put("renavam", dto.getRenavam());
		filter.put("chassi", dto.getChassi());
		filter.put("modelo", dto.getModelo());
		filter.put("anoModelo", dto.getAnoModelo());
		filter.put("anoFabricacao", dto.getAnoFabricacao());
		filter.put("cor", dto.getCor());
		filter.put("isVendido", dto.getIsVendido());
		return this.sqlSession.selectList("VeiculoRepository.findByVeiculos",filter);
	}
	
	
}
