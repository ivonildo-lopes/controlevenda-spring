package com.arquitetura.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arquitetura.model.Veiculo;

@Repository
public interface VeiculoDao extends JpaRepository<Veiculo, Long> {

}
