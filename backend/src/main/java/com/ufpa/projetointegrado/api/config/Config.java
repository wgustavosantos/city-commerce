package com.ufpa.projetointegrado.api.config;

import org.springframework.context.annotation.Bean;

import com.ufpa.projetointegrado.domain.service.EmailService;
import com.ufpa.projetointegrado.domain.service.MockEmailService;
import com.ufpa.projetointegrado.domain.service.SmtpEmailService;

public class Config {
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
