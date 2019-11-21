package com.co.app.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.NoOpPing;

@Profile("!local")
public class RibbonConfiguration {

	@Bean
	public IPing ribbonPing(final IClientConfig config) {
		return new NoOpPing();
	}

	@Bean
	public IRule ribbonRule(final IClientConfig config) {
		return new AvailabilityFilteringRule();
	}
}
