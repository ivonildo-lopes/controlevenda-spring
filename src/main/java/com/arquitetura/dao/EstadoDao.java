package com.arquitetura.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arquitetura.model.Estado;

@Repository
public interface EstadoDao extends JpaRepository<Estado, Long> {
	
	@Query(value = "From Estado e where e.sigla = :sigla")
	Estado findByEstadoSigla(@Param("sigla") String sigla);
	
	@Query(value = "From Estado e where e.id in (:ids)")
	List<Estado> findByIds(@Param("ids") List ids);
	
}
