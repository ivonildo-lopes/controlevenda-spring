package com.arquitetura.DTO;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class OrcamentoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private Long id;
	
	private Double valor;
	
	private String descricao;
	
	private VeiculoDto veiculo;
	private String veiculoDto;
	
	private Date data;

}
