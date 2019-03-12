/**
 * 
 */
package com.co.app.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.app.auth.domain.AuthRole;

/**
 * @author alobaton
 *
 */
public interface AuthRoleRepository extends JpaRepository<AuthRole, String> {

}
