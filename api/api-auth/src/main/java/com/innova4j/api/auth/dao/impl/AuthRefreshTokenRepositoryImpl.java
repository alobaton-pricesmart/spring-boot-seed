/**
 * 
 */
package com.innova4j.api.auth.dao.impl;

import java.util.Map;

import javax.validation.constraints.NotNull;

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
	public void customDelete(@NotNull Map<String, Object> pk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean customExists(@NotNull Map<String, Object> pk) {
		// TODO Auto-generated method stub
		return false;
	}


	

}
