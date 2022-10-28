
package br.com.schoolapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.schoolapi.model.ErrorResponse;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleConnversion(RuntimeException ex) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
				ex.getMessage(), ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}



