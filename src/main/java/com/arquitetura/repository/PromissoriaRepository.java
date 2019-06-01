package com.arquitetura.repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arquitetura.DTO.PromissoriaConsultaDto;
import com.arquitetura.DTO.PromissoriaDto;

@Component
public class PromissoriaRepository implements Serializable{

	
	private static final long serialVersionUID = -1084757059744145638L;
	private final transient SqlSession sqlSession;
	
	@Autowired
	public PromissoriaRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<PromissoriaDto> findByPromissoria(PromissoriaConsultaDto dto ) {
		Map<String, Object> filter = new HashMap<>();
		filter.put("idCliente", dto.getIdCliente());
		filter.put("dataInicio", dto.getDataInicio());
		filter.put("dataFim", dto.getDataFim());
		return this.sqlSession.selectList("PromissoriaRepository.findByPromissoria",filter);
	}
	
	public void atualizaPromissoriaPaga(Long id) {
		Map<String, Object> filter = new HashMap<>();
		filter.put("id", id);
		this.sqlSession.update("PromissoriaRepository.updatePromissoria",filter);
	}
	
	
}
