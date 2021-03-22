/**
 * 
 */
package com.co.app.auth.services.impl;

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

import com.co.app.auth.repository.AuthRoleRepository;
import com.co.app.auth.services.AuthRoleService;
import com.co.app.commons.domain.AuthRole;
import com.co.app.commons.exception.CustomDuplicateKeyException;
import com.co.app.commons.exception.RegisterNotFoundException;
import com.co.app.message.constants.MessageConstants;
import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 *
 */
@Service
public class AuthRoleServiceImpl implements AuthRoleService {

	@Autowired
	private AuthRoleRepository roleRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#create(java.lang.Object)
	 */
	@Override
	public AuthRole create(AuthRole domain) {
		String id = domain.getId();
		if (id != null && roleRepository.existsById(id)) {
			throw new CustomDuplicateKeyException(MessageConstants.ERROR_GENERAL_REGISTER_ALREADY_EXISTS,
					MessageConstants.AUTH_ROLE_NAME, id);
		}

		return roleRepository.save(domain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#get(java.lang.Object)
	 */
	@Override
	public AuthRole get(String id) {
		return roleRepository.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(MessageConstants.ERROR_GENERAL_REGISTER_NOT_FOUND,
						MessageConstants.AUTH_ROLE_NAME, id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#update(java.lang.Object)
	 */
	@Override
	public AuthRole update(AuthRole domain) {
		String id = domain.getId();
		if (id != null && !roleRepository.existsById(id)) {
			throw new RegisterNotFoundException(MessageConstants.ERROR_GENERAL_REGISTER_ALREADY_EXISTS,
					MessageConstants.AUTH_ROLE_NAME, id);
		}
		return roleRepository.save(domain);
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

		roleRepository.deleteById(id);

		return domain;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.service.BaseService#exists(java.lang.Object)
	 */
	@Override
	public boolean exists(String id) {
		return roleRepository.existsById(id);
	}

	@Override
	public Page<AuthRole> getAll(Predicate predicate, Pageable pageable) {
		return roleRepository.findAll(predicate, pageable);
	}

	@Override
	public List<AuthRole> getAll(Predicate predicate) {
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(roleRepository.findAll(predicate).iterator(), Spliterator.ORDERED),
				false).collect(Collectors.<AuthRole>toList());
	}

}
