/**
 * 
 */
package com.alobaton.api.auth.services.user;

import com.alobaton.api.auth.dto.AuthUserDto;
import com.alobaton.api.commons.service.BasePagedService;

/**
 * @author alobaton
 *
 */
public interface AuthUserService extends BasePagedService<AuthUserDto, String> {

	void resetPassword(String nickname);
}
