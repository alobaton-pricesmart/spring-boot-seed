/**
 * 
 */
package com.innova4j.api.auth.dao.impl;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innova4j.api.auth.dao.CustomAuthTokenRepository;
import com.innova4j.api.auth.domain.AuthToken;

/**
 * @author innova4j-team
 *
 */
public class AuthTokenRepositoryImpl implements CustomAuthTokenRepository {

	@Override
	public AuthToken customSave(@NotNull AuthToken domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthToken customGet(@NotNull Map<String, Object> pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthToken> customGetAll(@NotNull Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AuthToken> customGetAll(@NotNull Map<String, Object> parameters, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void customDelete(@NotNull Map<String, Object> pk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean customExists(@NotNull Map<String, Object> pk) {
		// TODO Auto-generated method stub
		return false;
	}

}
