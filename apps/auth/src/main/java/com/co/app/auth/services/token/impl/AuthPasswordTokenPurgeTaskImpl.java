/**
 * 
 */
package com.co.app.auth.services.token.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.app.auth.dao.AuthPasswordTokenRepository;
import com.co.app.auth.services.token.AuthPurgeTask;

/**
 * @author alobaton
 *
 */
@Service
@Transactional
public class AuthPasswordTokenPurgeTaskImpl implements AuthPurgeTask {

	@Autowired
	private AuthPasswordTokenRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.auth.services.AuthPurgeTask#purgeExpired()
	 */
	@Override
	@Scheduled(cron = "${purge.cron.expression}")
	public void purgeExpired() {
		LocalDateTime now = LocalDateTime.now();
		repository.deleteByExpiresAtLessThan(now);
	}

}
