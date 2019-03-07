/**
 * 
 */
package com.co.app.auth.services.user.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import com.co.app.auth.dao.AuthPasswordTokenRepository;
import com.co.app.auth.dao.AuthUserRepository;
import com.co.app.auth.domain.AuthPasswordToken;
import com.co.app.auth.domain.AuthPasswordTokenId;
import com.co.app.auth.domain.AuthUser;
import com.co.app.auth.dto.AuthUserDto;
import com.co.app.auth.services.encoder.HashEncoder;
import com.co.app.auth.services.user.AuthUserService;
import com.co.app.commons.exception.RegisterNotFoundException;
import com.co.app.email.service.EmailService;
import com.co.app.email.utils.EmailContentBuilder;
import com.co.app.message.service.MessageService;

/**
 * @author alobaton
 *
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

	@Value("${auth.password.validity-seconds}")
	private int validitySeconds;

	@Value("${email.from}")
	private String from;

	@Autowired
	private AuthUserRepository userRepository;

	@Autowired
	private AuthPasswordTokenRepository passwordTokenRepository;

	@Autowired
	private HashEncoder hashEncoder;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private TemplateEngine emailTemplateEngine;

	@Autowired
	private EmailService emailService;

	@Autowired
	private MessageService messageService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BasePagedService#getAll(java.lang.Object,
	 * org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<AuthUserDto> getAll(AuthUserDto dto, Pageable pageable) {
		Example<AuthUser> example = Example.of(AuthUser.CONVERTER.apply(dto));

		Page<AuthUser> result = userRepository.findAll(example, pageable);

		return new PageImpl<AuthUserDto>(
				result.getContent().stream().map(AuthUserDto.CONVERTER).collect(Collectors.<AuthUserDto>toList()),
				pageable, result.getTotalElements());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#create(java.lang.Object)
	 */
	@Override
	public AuthUserDto create(AuthUserDto dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		AuthUser domain = userRepository.save(AuthUser.CONVERTER.apply(dto));

		return AuthUserDto.CONVERTER.apply(domain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#get(java.lang.Object)
	 */
	@Override
	public AuthUserDto get(String id) {
		AuthUser domain = userRepository.getOne(id);

		return AuthUserDto.CONVERTER.apply(domain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#customGet(java.lang.Object)
	 */
	@Override
	public AuthUserDto customGet(@NotNull AuthUserDto dto) {
		Example<AuthUser> example = Example.of(AuthUser.CONVERTER.apply(dto));

		AuthUser domain = userRepository.findOne(example)
				.orElseThrow(() -> new RegisterNotFoundException(AuthUser.class, Strings.EMPTY, dto.toString()));

		return AuthUserDto.CONVERTER.apply(domain);
	}

	@Override
	public List<AuthUserDto> getAll() {
		return userRepository.findAll().stream().map(AuthUserDto.CONVERTER).collect(Collectors.<AuthUserDto>toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#getAll(java.lang.Object)
	 */
	@Override
	public List<AuthUserDto> getAll(AuthUserDto dto) {
		Example<AuthUser> example = Example.of(AuthUser.CONVERTER.apply(dto));

		List<AuthUser> result = userRepository.findAll(example);

		return result.stream().map(AuthUserDto.CONVERTER).collect(Collectors.<AuthUserDto>toList());
	}

	@Override
	public AuthUserDto update(AuthUserDto dto) {
		AuthUser domain = userRepository.save(AuthUser.CONVERTER.apply(dto));

		return AuthUserDto.CONVERTER.apply(domain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#customUpdate(java.util.Map)
	 */
	@Override
	public AuthUserDto customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#delete(java.lang.Object)
	 */
	@Override
	public AuthUserDto delete(String id) {
		AuthUserDto dto = get(id);

		userRepository.deleteById(id);

		return dto;
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

		passwordToken = passwordTokenRepository.save(passwordToken);

		EmailContentBuilder builder = new EmailContentBuilder().emailTemplateEngine(emailTemplateEngine)
				.template("reset-password-email").parameter("reset-password.user", getFullName(domain))
				.parameter("reset-password.uri", String.format("?token=%s", token));

		emailService.sendEmail(domain.getEmail(), from, messageService.getMessage("reset-password.subject"),
				builder.build(), Boolean.TRUE);
	}

	private String getFullName(AuthUser user) {
		StringBuilder builder = new StringBuilder();
		builder.append(user.getName() == null || user.getName().isEmpty() ? user.getName() : Strings.EMPTY);
		builder.append(StringUtils.SPACE);
		builder.append(user.getLastName() == null || user.getLastName().isEmpty() ? user.getLastName() : Strings.EMPTY);

		return builder.toString();
	}

}
