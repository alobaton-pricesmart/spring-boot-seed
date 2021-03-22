/**
 * 
 */
package com.co.app.auth.constants;

import org.apache.commons.codec.digest.HmacAlgorithms;

/**
 * @author alobaton
 *
 */
public interface AuthConstants {

	String USERNAME = "username";
	String NICKNAME = "nickname";
	String PASSWORD = "password";

	String ROLES = "roles";
	String ROLE = "role";
	String ROLE_PREFIX = "ROLE_";
	// Default application role. Change it if you require...
	String DEFAULT_ROLE = "USER";

	String TOKEN = "token";
	String TOKEN_ID = "tokenId";
	String REFRESH_TOKEN_ID = "refreshTokenId";
	String AUTHENTICATION_ID = "authenticationId";

	String HMAC_ALGORITHM = HmacAlgorithms.HMAC_SHA_512.getName();

	String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";
}
