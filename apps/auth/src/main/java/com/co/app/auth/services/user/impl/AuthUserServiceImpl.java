/**
 * 
 */
package com.co.app.auth.services.user.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.co.app.auth.configuration.EmailConfiguration;
import com.co.app.auth.dao.AuthPasswordTokenRepository;
import com.co.app.auth.dao.AuthUserRepository;
import com.co.app.auth.domain.AuthPasswordToken;
import com.co.app.auth.domain.AuthPasswordTokenId;
import com.co.app.auth.domain.AuthUser;
import com.co.app.auth.services.encoder.HashEncoder;
import com.co.app.auth.services.user.AuthUserService;
import com.co.app.commons.exception.RegisterNotFoundException;
import com.co.app.email.dto.MailDto;
import com.co.app.email.service.EmailService;
import com.co.app.email.utils.EmailConstants;
import com.co.app.email.utils.EmailServiceFactoryObject;
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
		// TODO(alobaton): Validar si no existe.
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
				.orElseThrow(() -> new RegisterNotFoundException(AuthUser.class, AuthUser.NICKNAME, id));
	}

	@Override
	public List<AuthUser> getAll(Predicate predicate) {
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(userRepository.findAll(predicate).iterator(), Spliterator.ORDERED),
				false).collect(Collectors.<AuthUser>toList());
	}

	@Override
	public AuthUser update(AuthUser domain) {
		// TODO(alobaton): Validar si existe.
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
	public void resetPassword(String nickname) {
		AuthUser domain = new AuthUser();
		domain.setNickname(nickname);

		Example<AuthUser> example = Example.of(domain);

		domain = userRepository.findOne(example)
				.orElseThrow(() -> new RegisterNotFoundException(AuthUser.class, AuthUser.NICKNAME, nickname));

		AuthPasswordTokenId id = new AuthPasswordTokenId();
		id.setNickname(nickname);

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
		model.put("reset-password.user", getFullName(domain));
		model.put("reset-password.uri", String.format("?token=%s", token));
		mail.setModel(model);

		Map<String, Object> properties = new HashMap<>();
		model.put(EmailConstants.MAIL_HOST, emailConfiguration.getHost());
		model.put(EmailConstants.MAIL_PORT, emailConfiguration.getPort());
		model.put(EmailConstants.MAIL_USERNAME, emailConfiguration.getUsername());
		model.put(EmailConstants.MAIL_KEY, emailConfiguration.getPassword());
		model.put(EmailConstants.MAIL_DEFAULT_ENCODING, emailConfiguration.getDefaultEncoding());
		mail.setProperties(properties);

		EmailService service = emailServiceFactoryObject
				.emailService(EmailServiceFactoryObject.SMTP_EMAIL_SERVICE_IMPL);
		service.sendEmail(mail);

	}

	private String getFullName(AuthUser user) {
		StringBuilder builder = new StringBuilder();
		builder.append(user.getName() == null || user.getName().isEmpty() ? user.getName() : Strings.EMPTY);
		builder.append(StringUtils.SPACE);
		builder.append(user.getLastName() == null || user.getLastName().isEmpty() ? user.getLastName() : Strings.EMPTY);

		return builder.toString();
	}

}
