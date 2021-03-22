/**
 * 
 */
package com.co.app.email.service.impl;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.co.app.email.dto.MailDto;
import com.co.app.email.exception.EmailException;
import com.co.app.email.service.EmailService;
import com.co.app.email.utils.EmailConstants;

/**
 * @author alobaton
 *
 */
public class SmtpEmailServiceImpl implements EmailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailServiceImpl.class.getName());

	private SpringTemplateEngine templateEngine;

	public SmtpEmailServiceImpl(SpringTemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@Async
	@Override
	public void sendEmail(MailDto mail) {
		try {
			JavaMailSender emailSender = getJavaMailSender(mail.getProperties());

			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			if (mail.getText() != null) {
				String text = mail.getText();
				helper.setText(text, false);
			} else {

				Context context = new Context();
				context.setVariables(mail.getModel());

				String text = templateEngine.process(mail.getTemplate(), context);
				helper.setText(text, true);

				final Map<String, String> map = mail.getProperties().entrySet().stream()
						.filter(e -> e.getKey().startsWith("images"))
						.collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString()));

				map.forEach((k, v) -> {
					try {
						helper.addInline(v, new File("classpath:templates/images/" + v));
					} catch (MessagingException e1) {
						LOGGER.error("Error addInline", e1);
					}
				});
			}

			helper.setTo(String.join(",", mail.getTo()));

			helper.setFrom(mail.getFrom());
			helper.setSubject(mail.getSubject());

			if (mail.getAttachments() != null) {
				Iterator<String> it = mail.getAttachments().iterator();
				while (it.hasNext()) {
					FileSystemResource file = new FileSystemResource(new File(it.next()));
					helper.addAttachment(file.getFilename(), file);
				}
			}

			emailSender.send(message);
		} catch (MessagingException e) {
			LOGGER.error("Error sending mail", e);
			throw new EmailException("Error sending mail", e);
		}
	}

	/**
	 * @return Retorna el JavaMailSnder
	 */
	public JavaMailSender getJavaMailSender(Map<String, Object> properties) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setDefaultEncoding(String.valueOf(properties.get(EmailConstants.MAIL_DEFAULT_ENCODING)));
		mailSender.setHost(String.valueOf(properties.get(EmailConstants.MAIL_HOST)));
		mailSender.setPort(Integer.parseInt(String.valueOf(properties.get(EmailConstants.MAIL_PORT))));
		mailSender.setUsername(String.valueOf(properties.get(EmailConstants.MAIL_USERNAME)));
		mailSender.setPassword(String.valueOf(properties.get(EmailConstants.MAIL_KEY)));

		Properties javaMailProperties = new Properties();
		properties.forEach(javaMailProperties::put);
		mailSender.setJavaMailProperties(javaMailProperties);

		return mailSender;
	}
}
