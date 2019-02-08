/**
 * 
 */
package com.innova4j.api.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innova4j.api.auth.domain.AuthUser;

/**
 * @author alobaton
 *
 */
@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, String> {

}
