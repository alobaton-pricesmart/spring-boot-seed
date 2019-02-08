/**
 * 
 */
package com.innova4j.api.auth.services.user.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

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

import com.innova4j.api.auth.dao.AuthPasswordTokenRepository;
import com.innova4j.api.auth.dao.AuthUserRepository;
import com.innova4j.api.auth.domain.AuthPasswordToken;
import com.innova4j.api.auth.domain.AuthPasswordTokenId;
import com.innova4j.api.auth.domain.AuthUser;
import com.innova4j.api.auth.dto.AuthUserDto;
import com.innova4j.api.auth.services.encoder.HashEncoder;
import com.innova4j.api.auth.services.user.AuthUserService;
import com.innova4j.api.commons.exception.RegisterNotFoundException;
import com.innova4j.api.email.EmailContentBuilder;
import com.innova4j.api.email.service.EmailService;

/**
 * @author alobaton
 *
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

	@Value("${auth.password.validitySeconds}")
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

	@Override
	public Page<AuthUserDto> getAll(AuthUserDto dto, Pageable pageable) {
		Example<AuthUser> example = Example.of(AuthUser.CONVERTER.apply(dto));

		Page<AuthUser> result = userRepository.findAll(example, pageable);

		return new PageImpl<AuthUserDto>(
				result.getContent().stream().map(AuthUserDto.CONVERTER).collect(Collectors.<AuthUserDto>toList()),
				pageable, result.getTotalElements());
	}

	@Override
	public AuthUserDto create(AuthUserDto dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		AuthUser domain = userRepository.save(AuthUser.CONVERTER.apply(dto));

		return AuthUserDto.CONVERTER.apply(domain);
	}

	@Override
	public AuthUserDto get(String id) {
		AuthUser domain = userRepository.getOne(id);

		return AuthUserDto.CONVERTER.apply(domain);
	}

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

	@Override
	public AuthUserDto customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public AuthUserDto delete(String id) {
		AuthUserDto dto = get(id);

		userRepository.deleteById(id);

		return dto;
	}

	@Override
	public boolean exists(String id) {
		return userRepository.existsById(id);
	}

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

		// TODO(alobaton): Create password reset template
		EmailContentBuilder builder = new EmailContentBuilder().emailTemplateEngine(emailTemplateEngine)
				.template("template").parameter("key", "value");

		emailService.sendEmail(domain.getEmail(), from, "Subject", builder.build(), Boolean.TRUE);
	}

}
