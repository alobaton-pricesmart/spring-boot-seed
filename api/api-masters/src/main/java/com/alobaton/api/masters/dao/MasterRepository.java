/**
 * 
 */
package com.alobaton.api.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alobaton.api.masters.domain.Master;

/**
 * @author alobaton
 *
 */
public interface MasterRepository extends JpaRepository<Master, String> {

}
