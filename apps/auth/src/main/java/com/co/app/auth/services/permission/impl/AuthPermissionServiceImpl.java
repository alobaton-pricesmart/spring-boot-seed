/**
 * 
 */
package com.co.app.auth.services.permission.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.app.auth.dao.AuthPermissionRepository;
import com.co.app.auth.domain.AuthPermission;
import com.co.app.auth.dto.AuthPermissionDto;
import com.co.app.auth.services.permission.AuthPermissionService;
import com.co.app.commons.CommonsConstants;
import com.co.app.commons.exception.RegisterNotFoundException;

/**
 * @author alobaton
 *
 */
@Service
@Transactional
public class AuthPermissionServiceImpl implements AuthPermissionService {

	@Autowired
	private AuthPermissionRepository repository;

	@Override
	public Page<AuthPermissionDto> getAll(Pageable pageable) {
		Page<AuthPermission> result = repository.findAll(pageable);

		return new PageImpl<AuthPermissionDto>(result.getContent().stream().map(AuthPermissionDto.CONVERTER)
				.collect(Collectors.<AuthPermissionDto>toList()), pageable, result.getTotalElements());
	}

	@Override
	public Page<AuthPermissionDto> getAll(AuthPermissionDto dto, Pageable pageable) {
		Example<AuthPermission> example = Example.of(AuthPermission.CONVERTER.apply(dto));

		Page<AuthPermission> result = repository.findAll(example, pageable);

		return new PageImpl<AuthPermissionDto>(result.getContent().stream().map(AuthPermissionDto.CONVERTER)
				.collect(Collectors.<AuthPermissionDto>toList()), pageable, result.getTotalElements());
	}

	@Override
	public AuthPermissionDto create(AuthPermissionDto dto) {
		AuthPermission domain = repository.save(AuthPermission.CONVERTER.apply(dto));

		return AuthPermissionDto.CONVERTER.apply(domain);
	}

	@Override
	public AuthPermissionDto get(String id) {
		AuthPermission domain = repository.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(AuthPermission.class, CommonsConstants.ID, id));

		return AuthPermissionDto.CONVERTER.apply(domain);
	}

	@Override
	public AuthPermissionDto customGet(@NotNull AuthPermissionDto dto) {
		Example<AuthPermission> example = Example.of(AuthPermission.CONVERTER.apply(dto));

		AuthPermission domain = repository.findOne(example)
				.orElseThrow(() -> new RegisterNotFoundException(AuthPermission.class, Strings.EMPTY, dto.toString()));

		return AuthPermissionDto.CONVERTER.apply(domain);
	}

	@Override
	public List<AuthPermissionDto> getAll() {
		return repository.findAll().stream().map(AuthPermissionDto.CONVERTER)
				.collect(Collectors.<AuthPermissionDto>toList());
	}

	@Override
	public List<AuthPermissionDto> getAll(AuthPermissionDto dto) {
		Example<AuthPermission> example = Example.of(AuthPermission.CONVERTER.apply(dto));

		List<AuthPermission> result = repository.findAll(example);

		return result.stream().map(AuthPermissionDto.CONVERTER).collect(Collectors.<AuthPermissionDto>toList());
	}

	@Override
	public AuthPermissionDto update(AuthPermissionDto dto) {
		AuthPermission domain = repository.save(AuthPermission.CONVERTER.apply(dto));

		return AuthPermissionDto.CONVERTER.apply(domain);
	}

	@Override
	public AuthPermissionDto customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public AuthPermissionDto delete(String id) {
		AuthPermissionDto dto = get(id);

		repository.deleteById(id);

		return dto;
	}

	@Override
	public boolean exists(String id) {
		return repository.existsById(id);
	}

}
