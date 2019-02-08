/**
 * 
 */
package com.innova4j.api.auth.services.user;

import com.innova4j.api.auth.dto.AuthUserDto;
import com.innova4j.api.commons.service.BasePagedService;

/**
 * @author alobaton
 *
 */
public interface AuthUserService extends BasePagedService<AuthUserDto, String> {

	void resetPassword(String nickname);
}
