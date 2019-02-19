/**
 * 
 */
package com.co.app.auth.services.token;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alobaton
 *
 */
@Service
@Transactional
public interface AuthPurgeTask {

	public void purgeExpired();
}
