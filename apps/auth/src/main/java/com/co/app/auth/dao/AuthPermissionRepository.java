/**
 * 
 */
package com.co.app.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.app.auth.domain.AuthPermission;

/**
 * @author alobaton
 *
 */
@Repository
public interface AuthPermissionRepository extends JpaRepository<AuthPermission, String> {

}
