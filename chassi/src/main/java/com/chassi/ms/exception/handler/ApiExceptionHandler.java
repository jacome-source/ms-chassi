package com.chassi.ms.exception.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chassi.ms.api.response.ErrorMessage;
import com.chassi.ms.exception.BadInputRequestException;

/**
 * Exception Handler - faz o tratamento de exceções lançadas por API Rest.
 * Substitui um possível bloco catch, adicionando uma mensagem de erro adequada e HTTP_STATUS.
 */

// Anotação para que a classe seja tratada pelo exception handler
@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { BadInputRequestException.class, NumberFormatException.class })
	public ResponseEntity<ErrorMessage> handleBadInputRequestException(Exception ex) {
		var message = ex.getClass().getSimpleName() + " : " + ex.getMessage();
		var response = new ErrorMessage(message, "Verifique se o input está correto.");

		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(value = { IOException.class })
	public ResponseEntity<ErrorMessage> handleIoException(Exception ex) {
		var message = "Server error, cause " + ex.getClass().getSimpleName() + " : " + ex.getMessage();
		var response = new ErrorMessage(message, "Tente daqui a alguns minutos!");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}
