package com.arquitetura.DTO;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PromissoriaConsultaDto implements Serializable {

	private static final long serialVersionUID = 7612393255663802731L;

	private Long idCliente;
	
	private Date dataInicio;
	
	private Date dataFim;

}
