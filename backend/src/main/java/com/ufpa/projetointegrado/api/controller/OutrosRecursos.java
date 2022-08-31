package com.ufpa.projetointegrado.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/recursos")
public class OutrosRecursos {
	
	@GetMapping
	public ResponseEntity<String> recursos (){
		return ResponseEntity.ok("Se você chegou aqui, significa que você está autenticado e autorizado a acessar este endpoint");
	}
}
