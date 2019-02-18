/**
 * 
 */
package com.alobaton.api.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alobaton.api.auth.domain.AuthClientDetails;

/**
 * @author alobaton
 *
 */
@Repository
public interface AuthClientDetailsRepository extends JpaRepository<AuthClientDetails, String> {

}
