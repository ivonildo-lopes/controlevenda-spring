package com.arquitetura.repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arquitetura.DTO.OrcamentoConsultaDto;
import com.arquitetura.DTO.OrcamentoDto;

@Component
public class OrcamentoRepository implements Serializable{

	
	private static final long serialVersionUID = -1641993811086087354L;
	private final transient SqlSession sqlSession;
	
	@Autowired
	public OrcamentoRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<OrcamentoDto> findByOrcamento(OrcamentoConsultaDto dto ) {
		Map<String, Object> filter = new HashMap<>();
		filter.put("idVeiculo", dto.getIdVeiculo());
		filter.put("dataInicio", dto.getDataInicio());
		filter.put("dataFim", dto.getDataFim());
		return this.sqlSession.selectList("OrcamentoRepository.findByOrcamento",filter);
	}
	
	
}
