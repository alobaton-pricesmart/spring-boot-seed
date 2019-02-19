/**
 * 
 */
package com.co.app.settings.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.app.settings.domain.Settings;

/**
 * @author alobaton
 *
 */
public interface SettingsRepository extends JpaRepository<Settings, String> {

}
