package com.ufpa.projetointegrado.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpa.projetointegrado.domain.DTO.EmailDTO;
import com.ufpa.projetointegrado.domain.model.Usuario;
import com.ufpa.projetointegrado.domain.service.AuthService;
import com.ufpa.projetointegrado.domain.service.UsuarioService;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AuthService authService;

	@PostMapping
	public Usuario salvarUsuario(@RequestBody @Valid Usuario usuario) {

		return usuarioService.salvar(usuario);

	}

	@PutMapping(value = "/{id}")
	public Usuario atualizarUsuario(@RequestBody Usuario updateUsuario, @PathVariable Long id) {

		Usuario usuario;

		usuario = usuarioService.buscar(id);

		BeanUtils.copyProperties(updateUsuario, usuario, "id");
		return usuarioService.salvar(usuario);
	}

	@GetMapping(value = "/{id}")
	public Usuario buscarUsuario(@PathVariable Long id) {
		return usuarioService.buscar(id);
	}

	@GetMapping
	public List<Usuario> listarUsuarios() {
		return usuarioService.listar();
	}

	@DeleteMapping(value = "/{id}")
	public void deletarUsuario(@PathVariable Long id) {

		usuarioService.deletar(id);
	}
	
	@SuppressWarnings("static-access")
	@PostMapping(value = "/recuperar-senha")
	public ResponseEntity<?> forgot(@RequestBody EmailDTO emailDTO) {
		try {
			authService.sendNewPassword(emailDTO.getEmail());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.internalServerError().body(e.getMessage()).badRequest().build();
		}
		return ResponseEntity.noContent().build();
	}

}
