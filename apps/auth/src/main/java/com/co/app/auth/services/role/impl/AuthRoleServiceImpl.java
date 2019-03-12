/**
 * 
 */
package com.co.app.auth.services.role.impl;

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

import com.co.app.auth.dao.AuthRoleRepository;
import com.co.app.auth.domain.AuthRole;
import com.co.app.auth.dto.AuthRoleDto;
import com.co.app.auth.services.role.AuthRoleService;
import com.co.app.commons.exception.RegisterNotFoundException;

/**
 * @author alobaton
 *
 */
public class AuthRoleServiceImpl implements AuthRoleService {

	@Autowired
	private AuthRoleRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BasePagedService#getAll(java.lang.Object,
	 * org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<AuthRoleDto> getAll(AuthRoleDto dto, Pageable pageable) {
		Example<AuthRole> example = Example.of(AuthRole.CONVERTER.apply(dto));

		Page<AuthRole> result = repository.findAll(example, pageable);

		return new PageImpl<AuthRoleDto>(
				result.getContent().stream().map(AuthRoleDto.CONVERTER).collect(Collectors.<AuthRoleDto>toList()),
				pageable, result.getTotalElements());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#create(java.lang.Object)
	 */
	@Override
	public AuthRoleDto create(AuthRoleDto dto) {
		AuthRole domain = repository.save(AuthRole.CONVERTER.apply(dto));

		return AuthRoleDto.CONVERTER.apply(domain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#get(java.lang.Object)
	 */
	@Override
	public AuthRoleDto get(String id) {
		AuthRole domain = repository.getOne(id);

		return AuthRoleDto.CONVERTER.apply(domain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#customGet(java.lang.Object)
	 */
	@Override
	public AuthRoleDto customGet(@NotNull AuthRoleDto dto) {
		Example<AuthRole> example = Example.of(AuthRole.CONVERTER.apply(dto));

		AuthRole domain = repository.findOne(example)
				.orElseThrow(() -> new RegisterNotFoundException(AuthRole.class, Strings.EMPTY, dto.toString()));

		return AuthRoleDto.CONVERTER.apply(domain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#getAll()
	 */
	@Override
	public List<AuthRoleDto> getAll() {
		return repository.findAll().stream().map(AuthRoleDto.CONVERTER).collect(Collectors.<AuthRoleDto>toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#getAll(java.lang.Object)
	 */
	@Override
	public List<AuthRoleDto> getAll(AuthRoleDto dto) {
		Example<AuthRole> example = Example.of(AuthRole.CONVERTER.apply(dto));

		List<AuthRole> result = repository.findAll(example);

		return result.stream().map(AuthRoleDto.CONVERTER).collect(Collectors.<AuthRoleDto>toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#update(java.lang.Object)
	 */
	@Override
	public AuthRoleDto update(AuthRoleDto dto) {
		AuthRole domain = repository.save(AuthRole.CONVERTER.apply(dto));

		return AuthRoleDto.CONVERTER.apply(domain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#customUpdate(java.util.Map)
	 */
	@Override
	public AuthRoleDto customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#delete(java.lang.Object)
	 */
	@Override
	public AuthRoleDto delete(String id) {
		AuthRoleDto dto = get(id);

		repository.deleteById(id);

		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#exists(java.lang.Object)
	 */
	@Override
	public boolean exists(String id) {
		return repository.existsById(id);
	}

}
