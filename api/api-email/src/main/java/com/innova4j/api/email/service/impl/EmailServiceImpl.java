/**
 * 
 */
package com.innova4j.api.email.service.impl;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import com.innova4j.api.email.service.EmailService;

/**
 * @author innova4j-team
 *
 */
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender sender;

	private static final Log LOGGER = LogFactory.getLog(EmailServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innova4j.api.email.service.EmailService#sendEmail(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Async
	public void sendEmail(String to, String from, String subject, String text, boolean html) {
		LOGGER.debug(String.format("Sending email to %s ...", to));

		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, html);

			sender.send(message);
			LOGGER.debug("Email sent!");

		} catch (Exception e) {
			LOGGER.error("Error sending email", e);
		}
	}

}
