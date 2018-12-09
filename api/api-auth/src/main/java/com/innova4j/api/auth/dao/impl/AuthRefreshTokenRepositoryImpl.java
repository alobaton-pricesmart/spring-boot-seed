/**
 * 
 */
package com.innova4j.api.auth.dao.impl;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innova4j.api.auth.dao.CustomAuthRefreshTokenRepository;
import com.innova4j.api.auth.domain.AuthRefreshToken;

/**
 * @author innova4j-team
 *
 */
public class AuthRefreshTokenRepositoryImpl implements CustomAuthRefreshTokenRepository {

	@Override
	public AuthRefreshToken customSave(@NotNull AuthRefreshToken domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthRefreshToken customGet(@NotNull Map<String, Object> pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthRefreshToken> customGetAll(@NotNull Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AuthRefreshToken> customGetAll(@NotNull Map<String, Object> parameters, Pageable pageable) {
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
