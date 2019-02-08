/**
 * 
 */
package com.innova4j.api.email.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @author alobaton
 *
 */
public interface EmailService {

	@Async
	void sendEmail(String to, String from, String subject, String text, boolean html);

}