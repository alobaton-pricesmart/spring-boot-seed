/**
 * 
 */
package com.innova4j.api.loader.runners.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author innova4j-team
 *
 */
@Component
public abstract class BaseRunner implements CommandLineRunner {

	private static final Log LOGGER = LogFactory.getLog(BaseRunner.class);

	protected String name;

	public BaseRunner(String name) {
		this.name = name;
	}

	protected abstract void load(String... args);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... args) throws Exception {
		LOGGER.info(String.format("Running %s...", name));
		load(args);
	}

}
