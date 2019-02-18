/**
 * 
 */
package com.alobaton.api.auth.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alobaton.api.auth.domain.AuthPasswordToken;
import com.alobaton.api.auth.domain.AuthPasswordTokenId;

/**
 * @author alobaton
 *
 */
@Repository
public interface AuthPasswordTokenRepository extends JpaRepository<AuthPasswordToken, AuthPasswordTokenId> {

	void deleteByExpiresAtLessThan(LocalDateTime date);

}
