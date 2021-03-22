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

import com.co.app.auth.repository.AuthPermissionRepository;
import com.co.app.auth.services.AuthPermissionService;
import com.co.app.commons.domain.AuthPermission;
import com.co.app.commons.exception.CustomDuplicateKeyException;
import com.co.app.commons.exception.RegisterNotFoundException;
import com.co.app.message.constants.MessageConstants;
import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 *
 */
@Service
public class AuthPermissionServiceImpl implements AuthPermissionService {

	@Autowired
	private AuthPermissionRepository permissionRepository;

	@Override
	public AuthPermission create(AuthPermission domain) {
		String id = domain.getId();
		if (id != null && permissionRepository.existsById(id)) {
			throw new CustomDuplicateKeyException(MessageConstants.ERROR_GENERAL_REGISTER_ALREADY_EXISTS,
					MessageConstants.AUTH_PERMISSION_NAME, id);
		}
		return permissionRepository.save(domain);
	}

	@Override
	public AuthPermission get(String id) {
		return permissionRepository.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(MessageConstants.ERROR_GENERAL_REGISTER_NOT_FOUND,
						MessageConstants.AUTH_PERMISSION_NAME, id));
	}

	@Override
	public AuthPermission update(AuthPermission domain) {
		String id = domain.getId();
		if (id != null && !permissionRepository.existsById(id)) {
			throw new RegisterNotFoundException(MessageConstants.ERROR_GENERAL_REGISTER_ALREADY_EXISTS,
					MessageConstants.AUTH_PERMISSION_NAME, id);
		}
		return permissionRepository.save(domain);
	}

	@Override
	public AuthPermission customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public AuthPermission delete(String id) {
		AuthPermission domain = get(id);

		permissionRepository.deleteById(id);

		return domain;
	}

	@Override
	public boolean exists(String id) {
		return permissionRepository.existsById(id);
	}

	@Override
	public Page<AuthPermission> getAll(Predicate predicate, Pageable pageable) {
		return permissionRepository.findAll(predicate, pageable);
	}

	@Override
	public List<AuthPermission> getAll(Predicate predicate) {
		return StreamSupport.stream(Spliterators
				.spliteratorUnknownSize(permissionRepository.findAll(predicate).iterator(), Spliterator.ORDERED), false)
				.collect(Collectors.<AuthPermission>toList());
	}

}
