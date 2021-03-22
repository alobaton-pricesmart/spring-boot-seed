/**
 * 
 */
package com.co.app.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.co.app.commons.domain.AuthUser;
import com.co.app.commons.domain.QAuthUser;
import com.co.app.commons.repository.BaseRepository;

/**
 * @author alobaton
 *
 */
@Repository
public interface AuthUserRepository
		extends JpaRepository<AuthUser, String>, QuerydslPredicateExecutor<AuthUser>, BaseRepository<QAuthUser> {

}
