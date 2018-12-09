/**
 * 
 */
package com.innova4j.api.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innova4j.api.auth.domain.AuthToken;
import com.innova4j.api.auth.domain.AuthTokenId;

/**
 * @author innova4j-team
 *
 */
@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, AuthTokenId>, CustomAuthTokenRepository {

}
