/**
 * 
 */
package com.alobaton.api.settings.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alobaton.api.settings.domain.Settings;

/**
 * @author alobaton
 *
 */
public interface SettingsRepository extends JpaRepository<Settings, String> {

}
