package com.ufpa.projetointegrado.domain.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufpa.projetointegrado.domain.model.Usuario;
import com.ufpa.projetointegrado.domain.repository.UsuarioRepository;

@Service
public class AuthService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private EmailService emailService;
	
	public void sendNewPassword(String email) throws Exception {
		
		

		if (email != null) {

			Usuario usuario = usuarioRepository.findByEmail(email);

			if (usuario == null) {
				throw new Exception("Email não encontrado");
			}

			String newPass = newPassword();

			usuario.setPassword(encoder.encode(newPass));

			usuarioRepository.save(usuario);

			emailService.sendNewPasswordEmail(usuario, newPass);

		}

	}

	private String newPassword() {

		char[] vet = new char[10];

		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}

		return new String(vet);
	}

	private char randomChar() { /* gera caractere alfanumerico aleatorio */

		Random rand = new Random();

		int cont = rand.nextInt(3); /* Sempre que randomChar for invocado, cont muda entre 0 ate 2 */

		if (cont == 0) { // Para caractere numerico
			char ch = (char) (rand.nextInt(10) + 48); // Na tbl unicode, 48 é o primeiro digito de numero
			return ch;

		} else if (cont == 1) { // Para caractere letra maiuscula

			char ch = (char) (rand.nextInt(26) + 65); // Na tbl unicode, 65 é o primeiro digito de letra maiuscula
			return ch;
		} else { // Para caractere letra minuscula

			char ch = (char) (rand.nextInt(26) + 97); // Na tbl unicode, 97 é o primeiro digito de letra minuscula
			return ch;

		}
	}
}
