package com.arquitetura.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponseDto {
	
	private String messagem;

    private Boolean success;

    private Object conteudo;

    
    
	public ResponseDto(String messagem, Boolean success, Object conteudo) {
		super();
		this.messagem = messagem;
		this.success = success;
		this.conteudo = conteudo;
	}
	
	public ResponseDto(String messagem) {
        this.messagem = messagem;
    }

    public ResponseDto(String messagem, Boolean success) {
        this.messagem = messagem;
        this.success = success;
    }
    
    

}
