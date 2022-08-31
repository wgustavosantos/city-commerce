package com.ufpa.projetointegrado.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ufpa.projetointegrado.domain.model.Usuario;
import com.ufpa.projetointegrado.domain.repository.UsuarioRepository;
import com.ufpa.projetointegrado.security.UserSS;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if(usuario == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(usuario.getId(), usuario.getEmail(), usuario.getPassword(), usuario.getPerfis());
	}

}
