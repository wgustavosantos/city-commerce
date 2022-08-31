package com.ufpa.projetointegrado.domain.service;

import org.springframework.mail.SimpleMailMessage;

import com.ufpa.projetointegrado.domain.model.Usuario;

public interface EmailService {

	void senderUserRegistrationConfirmation(Usuario usuario);
	
	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Usuario usuario, String newPass);
	
}
