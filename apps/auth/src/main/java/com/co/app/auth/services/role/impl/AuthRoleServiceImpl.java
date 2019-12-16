/**
 * 
 */
package com.co.app.auth.services.role.impl;

import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.app.auth.dao.AuthRoleRepository;
import com.co.app.auth.domain.AuthRole;
import com.co.app.auth.services.role.AuthRoleService;
import com.co.app.commons.CommonsConstants;
import com.co.app.commons.exception.RegisterNotFoundException;
import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 *
 */
@Service
@Transactional
public class AuthRoleServiceImpl implements AuthRoleService {

	@Autowired
	private AuthRoleRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#create(java.lang.Object)
	 */
	@Override
	public AuthRole create(AuthRole domain) {
		// TODO(alobaton): Validar si no existe.
		return repository.save(domain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#get(java.lang.Object)
	 */
	@Override
	public AuthRole get(String id) {
		return repository.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(AuthRole.class, CommonsConstants.ID, id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#update(java.lang.Object)
	 */
	@Override
	public AuthRole update(AuthRole domain) {
		// TODO(alobaton): Validar si existe.
		return repository.save(domain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#customUpdate(java.util.Map)
	 */
	@Override
	public AuthRole customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#delete(java.lang.Object)
	 */
	@Override
	public AuthRole delete(String id) {
		AuthRole domain = get(id);

		repository.deleteById(id);

		return domain;
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

	@Override
	public Page<AuthRole> getAll(Predicate predicate, Pageable pageable) {
		return repository.findAll(predicate, pageable);
	}

	@Override
	public List<AuthRole> getAll(Predicate predicate) {
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(repository.findAll(predicate).iterator(), Spliterator.ORDERED),
				false).collect(Collectors.<AuthRole>toList());
	}

}
