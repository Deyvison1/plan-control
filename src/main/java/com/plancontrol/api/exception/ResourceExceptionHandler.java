package com.plancontrol.api.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		String message = "Falha ao deletar registro. Contate um administrador ou verifique as possiveis causas.."; // ou pegue do ex.getMessage() se quiser mais técnico
		return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
	}
}
