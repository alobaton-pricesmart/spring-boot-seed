/**
 * 
 */
package com.innova4j.api.auth.services.token;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author innova4j-team
 *
 */
@Service
@Transactional
public interface AuthPurgeTask {

	public void purgeExpired();
}
