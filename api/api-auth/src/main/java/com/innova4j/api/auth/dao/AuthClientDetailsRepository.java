/**
 * 
 */
package com.innova4j.api.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innova4j.api.auth.domain.AuthClientDetails;

/**
 * @author innova4j-team
 *
 */
@Repository
public interface AuthClientDetailsRepository extends JpaRepository<AuthClientDetails, String> {

}
