/**
 * 
 */
package com.alobaton.api.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alobaton.api.auth.domain.AuthUser;

/**
 * @author alobaton
 *
 */
@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, String> {

}
