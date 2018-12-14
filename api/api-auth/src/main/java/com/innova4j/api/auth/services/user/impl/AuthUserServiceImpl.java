/**
 * 
 */
package com.innova4j.api.auth.services.user.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.innova4j.api.auth.AuthConstants;
import com.innova4j.api.auth.dao.AuthTokenRepository;
import com.innova4j.api.auth.dao.AuthUserRepository;
import com.innova4j.api.auth.domain.AuthToken;
import com.innova4j.api.auth.domain.AuthTokenId;
import com.innova4j.api.auth.domain.AuthUser;
import com.innova4j.api.auth.dto.AuthUserDto;
import com.innova4j.api.auth.services.encoder.HashEncoder;
import com.innova4j.api.auth.services.user.AuthUserService;
import com.innova4j.api.commons.exception.RegisterNotFoundException;

/**
 * @author innova4j-team
 *
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

	@Autowired
	private AuthUserRepository repository;

	@Autowired
	private AuthTokenRepository tokenRepository;

	@Autowired
	private HashEncoder encoder;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Page<AuthUserDto> getAll(AuthUserDto dto, Pageable pageable) {
		Example<AuthUser> example = Example.of(AuthUser.CONVERTER.apply(dto));

		Page<AuthUser> result = repository.findAll(example, pageable);

		return new PageImpl<AuthUserDto>(
				result.getContent().stream().map(AuthUserDto.CONVERTER).collect(Collectors.<AuthUserDto>toList()),
				pageable, result.getTotalElements());
	}

	@Override
	public AuthUserDto create(AuthUserDto dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		AuthUser user = repository.save(AuthUser.CONVERTER.apply(dto));

		return AuthUserDto.CONVERTER.apply(user);
	}

	@Override
	public AuthUserDto get(String id) {
		AuthUser user = repository.getOne(id);

		return AuthUserDto.CONVERTER.apply(user);
	}

	@Override
	public AuthUserDto customGet(@NotNull AuthUserDto dto) {
		Example<AuthUser> example = Example.of(AuthUser.CONVERTER.apply(dto));

		AuthUser user = repository.findOne(example)
				.orElseThrow(() -> new RegisterNotFoundException(AuthUser.class, Strings.EMPTY, dto.toString()));

		return AuthUserDto.CONVERTER.apply(user);
	}

	@Override
	public List<AuthUserDto> getAll() {
		return repository.findAll().stream().map(AuthUserDto.CONVERTER).collect(Collectors.<AuthUserDto>toList());
	}

	@Override
	public List<AuthUserDto> getAll(AuthUserDto dto) {
		Example<AuthUser> example = Example.of(AuthUser.CONVERTER.apply(dto));

		List<AuthUser> result = repository.findAll(example);

		return result.stream().map(AuthUserDto.CONVERTER).collect(Collectors.<AuthUserDto>toList());
	}

	@Override
	public AuthUserDto update(AuthUserDto dto) {
		AuthUser user = repository.save(AuthUser.CONVERTER.apply(dto));

		return AuthUserDto.CONVERTER.apply(user);
	}

	@Override
	public AuthUserDto customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public AuthUserDto delete(String id) {
		AuthUserDto dto = get(id);

		repository.deleteById(id);

		return dto;
	}

	@Override
	public boolean exists(String id) {
		return repository.existsById(id);
	}

	@Override
	public AuthUserDto getByAccessToken(String token) {
		AuthTokenId id = new AuthTokenId();
		id.setTokenId(encoder.encode(token));

		AuthToken t = new AuthToken();
		t.setId(id);

		Example<AuthToken> example = Example.of(t);

		t = tokenRepository.findOne(example)
				.orElseThrow(() -> new RegisterNotFoundException(AuthToken.class, AuthConstants.TOKEN_ID, token));

		return get(t.getId().getNickname());
	}

}
