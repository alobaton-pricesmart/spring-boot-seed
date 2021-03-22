/**
 * 
 */
package com.co.app.auth.services.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.co.app.auth.configuration.EmailConfiguration;
import com.co.app.auth.repository.AuthPasswordTokenRepository;
import com.co.app.auth.repository.AuthUserRepository;
import com.co.app.auth.services.AuthUserService;
import com.co.app.auth.services.HashEncoder;
import com.co.app.commons.domain.AuthPasswordToken;
import com.co.app.commons.domain.AuthPasswordTokenId;
import com.co.app.commons.domain.AuthUser;
import com.co.app.commons.domain.QAuthUser;
import com.co.app.commons.exception.CustomDuplicateKeyException;
import com.co.app.commons.exception.RegisterNotFoundException;
import com.co.app.commons.utils.StringUtil;
import com.co.app.email.dto.MailDto;
import com.co.app.email.service.EmailService;
import com.co.app.email.utils.EmailConstants;
import com.co.app.email.utils.EmailServiceFactoryObject;
import com.co.app.message.constants.MessageConstants;
import com.co.app.message.service.MessageService;
import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 *
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

	@Value("${reset-password.validity-seconds:1800}")
	private int validitySeconds;

	@Value("${reset-password.redirect-uri:}")
	private String redirectUri;

	@Autowired
	private AuthUserRepository userRepository;

	@Autowired
	private AuthPasswordTokenRepository passwordTokenRepository;

	@Autowired
	private HashEncoder hashEncoder;

	@Autowired
	private EmailConfiguration emailConfiguration;

	@Autowired
	private EmailServiceFactoryObject emailServiceFactoryObject;

	@Autowired
	private MessageService messageService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BasePagedService#getAll(java.lang.Object,
	 * org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<AuthUser> getAll(Predicate predicate, Pageable pageable) {
		return userRepository.findAll(predicate, pageable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#create(java.lang.Object)
	 */
	@Override
	public AuthUser create(AuthUser domain) {
		String id = domain.getNickname();
		if (id != null && userRepository.existsById(id)) {
			throw new CustomDuplicateKeyException(MessageConstants.ERROR_GENERAL_REGISTER_ALREADY_EXISTS,
					MessageConstants.AUTH_USER_NAME, id);
		}
		return userRepository.save(domain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#get(java.lang.Object)
	 */
	@Override
	public AuthUser get(String id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(MessageConstants.ERROR_GENERAL_REGISTER_NOT_FOUND,
						MessageConstants.AUTH_USER_NAME, id));
	}

	@Override
	public List<AuthUser> getAll(Predicate predicate) {
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(userRepository.findAll(predicate).iterator(), Spliterator.ORDERED),
				false).collect(Collectors.<AuthUser>toList());
	}

	@Override
	public AuthUser update(AuthUser domain) {
		String id = domain.getNickname();
		if (id != null && !userRepository.existsById(id)) {
			throw new RegisterNotFoundException(MessageConstants.ERROR_GENERAL_REGISTER_ALREADY_EXISTS,
					MessageConstants.AUTH_USER_NAME, id);
		}

		return userRepository.save(domain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#customUpdate(java.util.Map)
	 */
	@Override
	public AuthUser customUpdate(Map<String, Object> domain) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#delete(java.lang.Object)
	 */
	@Override
	public AuthUser delete(String id) {
		AuthUser domain = get(id);

		userRepository.deleteById(id);

		return domain;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#exists(java.lang.Object)
	 */
	@Override
	public boolean exists(String id) {
		return userRepository.existsById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.co.app.auth.services.user.AuthUserService#resetPassword(java.lang.String)
	 */
	@Override
	public void recoveryPassword(String email) {
		QAuthUser qAuthUser = QAuthUser.authUser;
		AuthUser domain = getAll(qAuthUser.email.eq(email)).stream().findFirst()
				.orElseThrow(() -> new RegisterNotFoundException(MessageConstants.ERROR_GENERAL_REGISTER_ALREADY_EXISTS,
						MessageConstants.AUTH_USER_NAME, email));

		AuthPasswordTokenId id = new AuthPasswordTokenId();
		id.setNickname(domain.getNickname());

		String token = UUID.randomUUID().toString();
		id.setToken(hashEncoder.encode(token));

		AuthPasswordToken passwordToken = new AuthPasswordToken();
		passwordToken.setId(id);

		LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(validitySeconds);
		passwordToken.setExpiresAt(expiresAt);

		passwordTokenRepository.save(passwordToken);

		MailDto mail = new MailDto();
		mail.setFrom(emailConfiguration.getFrom());
		mail.addTo(domain.getEmail());
		mail.setSubject(messageService.getMessage("reset-password.subject"));
		mail.setTemplate("reset-password-email");

		Map<String, Object> model = new HashMap<>();
		model.put("userFullName", getFullName(domain));
		model.put("redirectUri", String.format("%s?token=%s", redirectUri, token));
		model.put("validityMinutes", StringUtil.toString(validitySeconds / 60));
		mail.setModel(model);

		Map<String, Object> properties = new HashMap<>();
		properties.put(EmailConstants.MAIL_HOST, emailConfiguration.getHost());
		properties.put(EmailConstants.MAIL_PORT, emailConfiguration.getPort());
		properties.put(EmailConstants.MAIL_USERNAME, emailConfiguration.getUsername());
		properties.put(EmailConstants.MAIL_KEY, emailConfiguration.getPassword());
		properties.put(EmailConstants.MAIL_DEFAULT_ENCODING, emailConfiguration.getDefaultEncoding());
		properties.put(EmailConstants.MAIL_SMTP_SSL_ENABLE, "true");
		properties.put(EmailConstants.MAIL_SMTP_AUTH, "true");
		properties.put(EmailConstants.MAIL_SMTP_STARTTLS_ENABLE, "true");
		properties.put(EmailConstants.MAIL_SMTP_SOCKETFACTORY_CLASS, "javax.net.ssl.SSLSocketFactory");
		properties.put(EmailConstants.MAIL_SMTP_SOCKETFACTORY_FALLBACK, "false");
		mail.setProperties(properties);

		EmailService service = emailServiceFactoryObject
				.emailService(EmailServiceFactoryObject.SMTP_EMAIL_SERVICE_IMPL);
		service.sendEmail(mail);

	}

	@Override
	public AuthUser createAndFlush(AuthUser authUser) {
		String id = authUser.getNickname();
		if (id != null && userRepository.existsById(id)) {
			throw new CustomDuplicateKeyException(MessageConstants.ERROR_GENERAL_REGISTER_ALREADY_EXISTS,
					MessageConstants.AUTH_USER_NAME, id);
		}
		return userRepository.saveAndFlush(authUser);
	}

	private String getFullName(AuthUser user) {
		StringBuilder builder = new StringBuilder();
		builder.append(!StringUtil.isNullOrEmpty(user.getName()) ? user.getName() : Strings.EMPTY);
		builder.append(StringUtil.SPACE);
		builder.append(!StringUtil.isNullOrEmpty(user.getLastName()) ? user.getLastName() : Strings.EMPTY);

		return StringUtil.trim(builder.toString());
	}

}
