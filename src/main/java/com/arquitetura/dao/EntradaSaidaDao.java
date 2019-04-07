package com.arquitetura.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arquitetura.model.EntradaSaida;

@Repository
public interface EntradaSaidaDao extends JpaRepository<EntradaSaida, Long> {

}
