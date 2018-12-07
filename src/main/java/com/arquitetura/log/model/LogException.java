package com.arquitetura.log.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "log_exception")
public @Data class LogException implements Serializable {


	private static final long serialVersionUID = 1822945231001462134L;

	@Id
    @GeneratedValue
    private Long id;

    @Column(name = "ds_log_exception", nullable = false, length = 50000)
    private String exception;

    @Column(name = "dt_exception", nullable = false)
    private Date dtException;

    @Column(name = "nm_user_logged", nullable = false)
    private String nameUser;
}