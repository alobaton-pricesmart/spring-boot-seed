/**
 * 
 */
package com.co.app.auth.services.permission.impl;

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

import com.co.app.auth.dao.AuthPermissionRepository;
import com.co.app.auth.domain.AuthPermission;
import com.co.app.auth.services.permission.AuthPermissionService;
import com.co.app.commons.CommonsConstants;
import com.co.app.commons.exception.RegisterNotFoundException;
import com.querydsl.core.types.Predicate;

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
	public AuthPermission create(AuthPermission domain) {
		return repository.save(domain);
	}

	@Override
	public AuthPermission get(String id) {
		return repository.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(AuthPermission.class, CommonsConstants.ID, id));
	}

	@Override
	public AuthPermission update(AuthPermission domain) {
		return repository.save(domain);
	}

	@Override
	public AuthPermission customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public AuthPermission delete(String id) {
		AuthPermission domain = get(id);

		repository.deleteById(id);

		return domain;
	}

	@Override
	public boolean exists(String id) {
		return repository.existsById(id);
	}

	@Override
	public Page<AuthPermission> getAll(Predicate predicate, Pageable pageable) {
		return repository.findAll(predicate, pageable);
	}

	@Override
	public List<AuthPermission> getAll(Predicate predicate) {
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(repository.findAll(predicate).iterator(), Spliterator.ORDERED),
				false).collect(Collectors.<AuthPermission>toList());
	}

}
