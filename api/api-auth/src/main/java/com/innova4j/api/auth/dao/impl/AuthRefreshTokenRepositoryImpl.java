/**
 * 
 */
package com.innova4j.api.auth.dao.impl;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;

import org.jooq.DSLContext;
import org.jooq.DeleteWhereStep;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;

import com.innova4j.api.auth.dao.CustomAuthRefreshTokenRepository;
import com.innova4j.api.auth.domain.AuthRefreshToken;

/**
 * @author innova4j-team
 *
 */
public class AuthRefreshTokenRepositoryImpl implements CustomAuthRefreshTokenRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	private DSLContext ctx;

	@Override
	public AuthRefreshToken customSave(@NotNull Map<String, Object> domain) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void customDelete(@NotNull Map<String, Object> domain) {
		DeleteWhereStep<Record> step = ctx.deleteFrom(DSL.table("auth_token"));

		for (String field : domain.keySet()) {
			step.where(DSL.field(field).eq(domain.get(field)));
		}

		String query = step.getSQL();
		Query result = entityManager.createNativeQuery(query);

		result.executeUpdate();
	}
}
