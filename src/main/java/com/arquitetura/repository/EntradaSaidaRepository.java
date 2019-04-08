package com.arquitetura.repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arquitetura.DTO.EntradaSaidaConsultaDto;
import com.arquitetura.DTO.EntradaSaidaDto;

@Component
public class EntradaSaidaRepository implements Serializable{

	private static final long serialVersionUID = -3791380518543804910L;
	
	private final transient SqlSession sqlSession;
	
	@Autowired
	public EntradaSaidaRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<EntradaSaidaConsultaDto> findByEntradaSaida(EntradaSaidaDto dto ) {
		Map<String, Object> filter = new HashMap<>();
		filter.put("tipo", dto.getTipo());
		filter.put("idVeiculo", dto.getIdVeiculo());
		filter.put("idCliente", dto.getIdCliente());
		filter.put("dataInicio", dto.getDataInicio());
		filter.put("dataFim", dto.getDataFim());
		return this.sqlSession.selectList("EntradaSaidaRepository.findByEntradaSaida",filter);
	}
	
	
}
