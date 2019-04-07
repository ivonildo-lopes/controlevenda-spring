package com.arquitetura.repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arquitetura.DTO.ClienteDto;

@Component
public class ClienteRepository implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -206293862806097260L;
	private final transient SqlSession sqlSession;
	
	@Autowired
	public ClienteRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<ClienteDto> findByClientes(ClienteDto dto ) {
		Map<String, Object> filter = new HashMap<>();
		filter.put("nome", dto.getNome());
		filter.put("cpf", dto.getCpf());
		filter.put("rg", dto.getRg());
		filter.put("email", dto.getEmail());
		filter.put("telefone", dto.getTelefone());
		filter.put("endereco", dto.getEndereco());
		return this.sqlSession.selectList("ClienteRepository.findByClientes",filter);
	}
	
	
}
