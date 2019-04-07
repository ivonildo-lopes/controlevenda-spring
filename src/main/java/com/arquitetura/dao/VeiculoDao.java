package com.arquitetura.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arquitetura.model.Veiculo;

@Repository
public interface VeiculoDao extends JpaRepository<Veiculo, Long> {
	
	@Query("select from Veiculo v where v.isVendido = false")
	public List<Veiculo> todosVeiculosNaLoja();

}
