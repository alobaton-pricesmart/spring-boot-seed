/**
 * 
 */
package com.co.app.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.app.masters.domain.Master;

/**
 * @author alobaton
 *
 */
@Repository
public interface MasterRepository extends JpaRepository<Master, String> {

}
