/**
 * 
 */
package com.innova4j.api.settings.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innova4j.api.settings.domain.Settings;

/**
 * @author alobaton
 *
 */
public interface SettingsRepository extends JpaRepository<Settings, String> {

}
