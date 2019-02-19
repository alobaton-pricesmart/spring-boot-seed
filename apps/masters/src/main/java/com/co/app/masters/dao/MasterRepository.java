/**
 * 
 */
package com.co.app.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.app.masters.domain.Master;

/**
 * @author alobaton
 *
 */
public interface MasterRepository extends JpaRepository<Master, String> {

}
