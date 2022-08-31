package com.ufpa.projetointegrado.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.ufpa.projetointegrado.domain.model.Usuario;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void senderUserRegistrationConfirmation(Usuario usuario) {

		SimpleMailMessage sm = prepareSimpleMailMessageFromUsuario(usuario);
		
		//sendEmail vem de EmailService, padrao Template Method
		sendEmail(sm);


	}

	protected SimpleMailMessage prepareSimpleMailMessageFromUsuario(Usuario usuario) {

		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Usuário cadastrado!");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(usuario.toString());
		return sm;
	}
	
	@Override
	public void sendNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(usuario, newPass);
		sendEmail(sm); /* da classe EmailService */
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Usuario usuario,
			String newPass) { /* protected p/ subclasse propepor o cabeçalho do metodo */

		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;

	}
}
