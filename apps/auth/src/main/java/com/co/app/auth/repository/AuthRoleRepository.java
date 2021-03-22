/**
 * 
 */
package com.co.app.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.co.app.commons.domain.AuthRole;
import com.co.app.commons.domain.QAuthRole;
import com.co.app.commons.repository.BaseRepository;

/**
 * @author alobaton
 *
 */
@Repository
public interface AuthRoleRepository
		extends JpaRepository<AuthRole, String>, QuerydslPredicateExecutor<AuthRole>, BaseRepository<QAuthRole> {

}
