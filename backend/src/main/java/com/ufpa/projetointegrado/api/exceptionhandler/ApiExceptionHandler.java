package com.ufpa.projetointegrado.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleData(DataIntegrityViolationException ex, WebRequest request){
		
		//String detalhe = "O parametro da url /api/usuario recebeu um valor inesperado pelo banco de dados";
		
		BodyProblem problem = new BodyProblem(HttpStatus.INTERNAL_SERVER_ERROR, "DataIntegrityViolationException", "Erro de SQL", "Constraint definida como Unique no Banco de Dados", LocalDateTime.now());
		
		return super.handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
