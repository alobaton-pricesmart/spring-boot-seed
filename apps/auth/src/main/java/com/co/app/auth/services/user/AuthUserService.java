/**
 * 
 */
package com.co.app.auth.services.user;

import com.co.app.commons.service.BasePagedService;
import com.co.app.auth.dto.AuthUserDto;

/**
 * @author alobaton
 *
 */
public interface AuthUserService extends BasePagedService<AuthUserDto, String> {

	void resetPassword(String nickname);
}
