/**
 * 
 */
package com.innova4j.api.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innova4j.api.auth.domain.AuthPasswordToken;
import com.innova4j.api.auth.domain.AuthPasswordTokenId;

/**
 * @author innova4j-team
 *
 */
@Repository
public interface AuthPasswordTokenRepository extends JpaRepository<AuthPasswordToken, AuthPasswordTokenId> {

}
