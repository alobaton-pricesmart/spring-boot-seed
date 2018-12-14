/**
 * 
 */
package com.innova4j.api.auth.dao.impl;

import java.util.Map;

import javax.validation.constraints.NotNull;

import com.innova4j.api.auth.dao.CustomAuthUserRepository;
import com.innova4j.api.auth.domain.AuthUser;

/**
 * @author innova4j-team
 *
 */
public class AuthUserRepositoryImpl implements CustomAuthUserRepository {

	@Override
	public AuthUser customSave(@NotNull Map<String, Object> domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void customDelete(@NotNull Map<String, Object> domain) {
		// TODO Auto-generated method stub
		
	}
}
