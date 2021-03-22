/**
 * 
 */
package com.co.app.auth.services;

import com.co.app.commons.domain.AuthUser;
import com.co.app.commons.service.BasePagedService;

/**
 * @author alobaton
 *
 */
public interface AuthUserService extends BasePagedService<AuthUser, String> {

	void recoveryPassword(String nickname);

	AuthUser createAndFlush(AuthUser authUser);
}
