package com.arquitetura.DTO;

import java.util.List;

import lombok.Data;

public @Data class ComboEntradaSaidaDto {

	List<VeiculoDto> veiculos;
	List<ClienteDto> clientes;

}
