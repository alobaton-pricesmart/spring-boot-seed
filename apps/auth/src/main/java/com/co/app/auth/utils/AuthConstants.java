/**
 * 
 */
package com.co.app.auth.utils;

import org.apache.commons.codec.digest.HmacAlgorithms;

/**
 * @author alobaton
 *
 */
public interface AuthConstants {

	String ROLES = "roles";
	String ROLE = "role";
	String ROLE_PREFIX = "ROLE_";

	String TOKEN = "token";
	String TOKEN_ID = "tokenId";
	String REFRESH_TOKEN_ID = "refreshTokenId";
	String AUTHENTICATION_ID = "authenticationId";

	String HMAC_ALGORITHM = HmacAlgorithms.HMAC_SHA_512.getName();

	String TIMEZONE = "EST";
}
