package com.arquitetura.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arquitetura.model.Produto;

@Repository
public interface ProdutoDao extends JpaRepository<Produto, Long> {

}
