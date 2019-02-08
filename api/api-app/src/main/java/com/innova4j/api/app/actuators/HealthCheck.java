/**
 * 
 */
package com.innova4j.api.app.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author alobaton
 *
 */
@Component
public class HealthCheck implements HealthIndicator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.actuate.health.HealthIndicator#health()
	 */
	@Override
	public Health health() {
		return Health.up().build();
	}
}