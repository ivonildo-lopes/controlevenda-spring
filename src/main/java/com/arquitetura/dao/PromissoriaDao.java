package com.arquitetura.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arquitetura.model.Promissoria;

@Repository
public interface PromissoriaDao extends JpaRepository<Promissoria, Long> {
	

}
