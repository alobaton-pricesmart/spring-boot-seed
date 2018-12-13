/**
 * 
 */
package com.innova4j.api.auth.services.user.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.innova4j.api.auth.dao.AuthUserRepository;
import com.innova4j.api.auth.domain.AuthUser;
import com.innova4j.api.auth.dto.AuthUserDto;
import com.innova4j.api.auth.services.user.AuthUserService;

/**
 * @author innova4j-team
 *
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

	@Autowired
	private AuthUserRepository repository;

	@Autowired
	private BCryptPasswordEncoder encoder;

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
		dto.setPassword(encoder.encode(dto.getPassword()));

		AuthUser user = repository.save(AuthUser.CONVERTER.apply(dto));

		return AuthUserDto.CONVERTER.apply(user);
	}

	@Override
	public AuthUserDto get(String id) {
		AuthUser user = repository.getOne(id);

		return AuthUserDto.CONVERTER.apply(user);
	}

	@Override
	public AuthUserDto customGet(@NotNull Map<String, Object> pk) {
		return null;
	}

	@Override
	public List<AuthUserDto> getAll() {
		return repository.findAll().stream().map(AuthUserDto.CONVERTER).collect(Collectors.<AuthUserDto>toList());
	}

	@Override
	public List<AuthUserDto> getAll(AuthUserDto dto) {
		return null;
	}

	@Override
	public AuthUserDto update(AuthUserDto dto) {
		AuthUser user = repository.save(AuthUser.CONVERTER.apply(dto));

		return AuthUserDto.CONVERTER.apply(user);
	}

	@Override
	public AuthUserDto customUpdate(Map<String, Object> dto) {
		return null;
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

}
