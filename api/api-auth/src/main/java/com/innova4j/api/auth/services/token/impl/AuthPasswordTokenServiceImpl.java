/**
 * 
 */
package com.innova4j.api.auth.services.token.impl;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.innova4j.api.auth.dao.AuthPasswordTokenRepository;
import com.innova4j.api.auth.domain.AuthPasswordToken;
import com.innova4j.api.auth.domain.AuthPasswordTokenId;
import com.innova4j.api.auth.services.token.AuthPasswordTokenService;
import com.innova4j.api.commons.exception.RegisterNotFoundException;

/**
 * @author innova4j-team
 *
 */
public class AuthPasswordTokenServiceImpl implements AuthPasswordTokenService {

	@Autowired
	private AuthPasswordTokenRepository repository;

	@Override
	public AuthPasswordToken create(AuthPasswordToken dto) {
		return repository.save(dto);
	}

	@Override
	public AuthPasswordToken get(AuthPasswordTokenId id) {
		return repository.getOne(id);
	}

	@Override
	public AuthPasswordToken customGet(@NotNull AuthPasswordToken dto) {
		Example<AuthPasswordToken> example = Example.of(dto);

		return repository.findOne(example).orElseThrow(
				() -> new RegisterNotFoundException(AuthPasswordToken.class, Strings.EMPTY, dto.toString()));
	}

	@Override
	public List<AuthPasswordToken> getAll() {
		return repository.findAll();
	}

	@Override
	public List<AuthPasswordToken> getAll(AuthPasswordToken dto) {
		Example<AuthPasswordToken> example = Example.of(dto);

		return repository.findAll(example);
	}

	@Override
	public AuthPasswordToken update(AuthPasswordToken dto) {
		return repository.save(dto);
	}

	@Override
	public AuthPasswordToken customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public AuthPasswordToken delete(AuthPasswordTokenId id) {
		AuthPasswordToken domain = get(id);

		repository.deleteById(id);

		return domain;
	}

	@Override
	public boolean exists(String id) {
		throw new UnsupportedOperationException();
	}

}
