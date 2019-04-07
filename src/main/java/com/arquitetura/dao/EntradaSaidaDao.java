package com.arquitetura.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arquitetura.model.EntradaSaida;

@Repository
public interface EntradaSaidaDao extends JpaRepository<EntradaSaida, Long> {
	
	@Query(value = "SELECT  count(id_veiculo) FROM entrada_saida where id_veiculo = :idVeiculo   group by id_veiculo",
		nativeQuery = true)
	public Integer verificaSeJafoiDadoEntrada(@Param("idVeiculo")Long idVeiculo);

}
