package com.arquitetura.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arquitetura.model.Cidade;

@Repository
public interface CidadeDao extends JpaRepository<Cidade, Long> {
	
}
