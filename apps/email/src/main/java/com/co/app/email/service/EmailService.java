/**
 * 
 */
package com.co.app.email.service;

import com.co.app.email.dto.MailDto;

/**
 * @author alobaton
 *
 */
public interface EmailService {

	/**
	 * Envia un correo.
	 * 
	 * @param mail Contenido del correo.
	 */
	public void sendEmail(MailDto mail);

}
