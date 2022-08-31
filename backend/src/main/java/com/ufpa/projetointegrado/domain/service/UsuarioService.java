package com.ufpa.projetointegrado.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufpa.projetointegrado.domain.model.Usuario;
import com.ufpa.projetointegrado.domain.model.enums.Perfil;
import com.ufpa.projetointegrado.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private EmailService emailService;
	

	public Usuario salvar(Usuario usuario) {
		
		usuario.addPerfil(Perfil.USUARIO);
		String passEncoded = encoder.encode(usuario.getPassword());
		usuario.setPassword(passEncoded);
		usuario = usuarioRepository.save(usuario);
		emailService.senderUserRegistrationConfirmation(usuario);
		return usuario;
	}

	public Usuario buscar(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id).get();
	}

	public List<Usuario> listar() {
		
		return usuarioRepository.findAll();
	}

	public void deletar(Long id) {
		 usuarioRepository.deleteById(id);
		
	}

}
