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
	public AuthToken customSave(@NotNull Map<String, Object> domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void customDelete(@NotNull Map<String, Object> domain) {
		// TODO Auto-generated method stub
		
	}

	
}
