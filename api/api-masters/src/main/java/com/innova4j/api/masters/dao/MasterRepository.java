/**
 * 
 */
package com.innova4j.api.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innova4j.api.masters.domain.Master;

/**
 * @author innova4j-team
 *
 */
public interface MasterRepository extends JpaRepository<Master, String> {

}
