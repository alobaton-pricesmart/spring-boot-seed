/**
 * 
 */
package com.co.app.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.app.auth.domain.AuthUser;

/**
 * @author alobaton
 *
 */
@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, String> {

}
