/**
 * 
 */
package com.co.app.email.utils;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.co.app.email.service.EmailService;
import com.co.app.email.service.impl.SmtpEmailServiceImpl;

/**
 * @author alobaton
 *
 */
@Component
public class EmailServiceFactoryObject {
	/**
	 * Aqui debemos añadir los identificadores para nuestra fabrica de objetos
	 * (Implementaciones de nuestro servicio de correos)
	 */
	public static final String SMTP_EMAIL_SERVICE_IMPL = "smtp";

	/**
	 * 
	 * @param impl Identificacion de la implementacion
	 * @return
	 */
	public EmailService emailService(@NotNull String impl) {
		switch (impl) {
		case SMTP_EMAIL_SERVICE_IMPL:
			return new SmtpEmailServiceImpl();
		default:
			return null;
		}
	}

}
