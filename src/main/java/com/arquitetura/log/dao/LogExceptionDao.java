package com.arquitetura.log.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arquitetura.log.model.LogException;

public interface LogExceptionDao extends JpaRepository<LogException, Long> {

}
