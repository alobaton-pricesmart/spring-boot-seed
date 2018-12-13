/**
 * 
 */
package com.innova4j.api.auth.dao.impl;

import java.util.Map;

import javax.validation.constraints.NotNull;

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
	public void customDelete(@NotNull Map<String, Object> pk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean customExists(@NotNull Map<String, Object> pk) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
