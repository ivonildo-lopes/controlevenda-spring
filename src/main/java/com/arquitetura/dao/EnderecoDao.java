package com.arquitetura.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arquitetura.model.Endereco;

@Repository
public interface EnderecoDao extends JpaRepository<Endereco, Long> {

}
