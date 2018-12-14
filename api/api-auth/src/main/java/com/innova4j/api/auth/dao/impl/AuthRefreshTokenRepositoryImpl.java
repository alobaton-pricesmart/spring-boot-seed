/**
 * 
 */
package com.innova4j.api.auth.dao.impl;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

import com.innova4j.api.auth.dao.CustomAuthRefreshTokenRepository;
import com.innova4j.api.auth.domain.AuthRefreshToken;

/**
 * @author innova4j-team
 *
 */
public class AuthRefreshTokenRepositoryImpl implements CustomAuthRefreshTokenRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public AuthRefreshToken customSave(@NotNull Map<String, Object> domain) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void customDelete(@NotNull Map<String, Object> domain) {
		// TODO Auto-generated method stub

	}
}
