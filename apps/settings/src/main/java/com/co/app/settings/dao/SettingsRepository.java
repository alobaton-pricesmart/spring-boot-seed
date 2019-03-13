/**
 * 
 */
package com.co.app.settings.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.app.settings.domain.Settings;

/**
 * @author alobaton
 *
 */
@Repository
public interface SettingsRepository extends JpaRepository<Settings, String> {

}
