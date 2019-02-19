/**
 * 
 */
package com.co.app.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.app.masters.domain.MasterType;

/**
 * @author alobaton
 *
 */
public interface MasterTypeRepository extends JpaRepository<MasterType, String> {

}
