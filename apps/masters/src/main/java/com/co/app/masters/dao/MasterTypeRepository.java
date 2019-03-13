/**
 * 
 */
package com.co.app.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.app.masters.domain.MasterType;

/**
 * @author alobaton
 *
 */
@Repository
public interface MasterTypeRepository extends JpaRepository<MasterType, String> {

}
