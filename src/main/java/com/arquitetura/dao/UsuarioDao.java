package com.arquitetura.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arquitetura.model.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {

	@Query(value = "select u from Usuario u where u.user = :user")
	Optional<Usuario> findByUser(String user);
}
