package com.arquitetura.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arquitetura.model.Cliente;

@Repository
public interface ClienteDao extends JpaRepository<Cliente, Long> {

}
