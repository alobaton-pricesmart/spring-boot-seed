/**
 * 
 */
package com.co.app.auth.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.co.app.auth.domain.AuthPasswordToken;
import com.co.app.auth.domain.AuthPasswordTokenId;

/**
 * @author alobaton
 *
 */
@Repository
public interface AuthPasswordTokenRepository extends JpaRepository<AuthPasswordToken, AuthPasswordTokenId>,
		QuerydslPredicateExecutor<AuthPasswordTokenRepository> {

	void deleteByExpiresAtLessThan(LocalDateTime date);

}
