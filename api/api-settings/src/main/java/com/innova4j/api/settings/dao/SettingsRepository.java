/**
 * 
 */
package com.innova4j.api.settings.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innova4j.api.settings.domain.Settings;

/**
 * @author innova4j-team
 *
 */
public interface SettingsRepository extends JpaRepository<Settings, String> {

}
