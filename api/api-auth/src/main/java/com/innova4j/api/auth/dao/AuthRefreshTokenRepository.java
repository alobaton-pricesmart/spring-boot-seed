/**
 * 
 */
package com.innova4j.api.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innova4j.api.auth.domain.AuthRefreshToken;
import com.innova4j.api.auth.domain.AuthRefreshTokenId;

/**
 * @author innova4j-team
 *
 */
@Repository
public interface AuthRefreshTokenRepository
		extends JpaRepository<AuthRefreshToken, AuthRefreshTokenId>, CustomAuthRefreshTokenRepository {

}
