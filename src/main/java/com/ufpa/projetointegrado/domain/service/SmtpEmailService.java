package com.ufpa.projetointegrado.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;

	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {

		LOG.info("Enviando email...");
		mailSender.send(msg);
		LOG.info("Email enviado");

	}

}
