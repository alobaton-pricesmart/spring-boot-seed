/**
 * 
 */
package com.co.app.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.co.app.commons.domain.AuthPermission;

/**
 * @author alobaton
 *
 */
@Repository
public interface AuthPermissionRepository
		extends JpaRepository<AuthPermission, String>, QuerydslPredicateExecutor<AuthPermission> {

}
