/**
 * 
 */
package com.innova4j.api.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innova4j.api.masters.domain.MasterType;

/**
 * @author alobaton
 *
 */
public interface MasterTypeRepository extends JpaRepository<MasterType, String> {

}
