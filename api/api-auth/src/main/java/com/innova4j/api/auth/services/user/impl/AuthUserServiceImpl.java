/**
 * 
 */
package com.innova4j.api.auth.services.user.impl;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.innova4j.api.auth.domain.AuthUser;
import com.innova4j.api.auth.services.user.AuthUserService;

/**
 * @author innova4j-team
 *
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

	@Override
	public Page<AuthUser> getAll(Map<String, Object> parameters, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthUser create(AuthUser domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthUser get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthUser customGet(@NotNull Map<String, Object> pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthUser> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthUser> getAll(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthUser update(AuthUser domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthUser customUpdate(Map<String, Object> domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthUser delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
