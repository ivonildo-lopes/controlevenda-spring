package com.arquitetura.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arquitetura.model.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {

}
