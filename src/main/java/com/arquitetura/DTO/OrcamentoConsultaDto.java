package com.arquitetura.DTO;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class OrcamentoConsultaDto implements Serializable {


	private static final long serialVersionUID = -744000069603474022L;

	private Long idVeiculo;
	
	private Date dataInicio;
	
	private Date dataFim;

}
