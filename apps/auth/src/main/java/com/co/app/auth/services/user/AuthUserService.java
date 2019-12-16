/**
 * 
 */
package com.co.app.auth.services.user;

import com.co.app.auth.domain.AuthUser;
import com.co.app.commons.service.BasePagedService;

/**
 * @author alobaton
 *
 */
public interface AuthUserService extends BasePagedService<AuthUser, String> {

	void resetPassword(String nickname);
}
