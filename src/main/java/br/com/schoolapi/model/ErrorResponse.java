package br.com.schoolapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

	private String code;
	
	private String message;
	
	private String details;
}

