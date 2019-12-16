/**
 * 
 */
package com.co.app.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.co.app.auth.domain.AuthClientDetails;

/**
 * @author alobaton
 *
 */
@Repository
public interface AuthClientDetailsRepository
		extends JpaRepository<AuthClientDetails, String>, QuerydslPredicateExecutor<AuthClientDetails> {

}
