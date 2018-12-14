/**
 * 
 */
package com.innova4j.api.auth.dao.impl;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.innova4j.api.auth.dao.CustomAuthTokenRepository;
import com.innova4j.api.auth.domain.AuthToken;

/**
 * @author innova4j-team
 *
 */
@Repository
@Transactional
public class AuthTokenRepositoryImpl implements CustomAuthTokenRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public AuthToken customSave(@NotNull Map<String, Object> domain) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void customDelete(@NotNull Map<String, Object> domain) {
	}

}
