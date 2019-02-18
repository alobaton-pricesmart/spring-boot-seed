/**
 * 
 */
package com.alobaton.api.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alobaton.api.masters.domain.MasterType;

/**
 * @author alobaton
 *
 */
public interface MasterTypeRepository extends JpaRepository<MasterType, String> {

}
