/**
 * 
 */
package com.co.app.auth;

import org.apache.commons.codec.digest.HmacAlgorithms;

/**
 * @author alobaton
 *
 */
public class AuthConstants {

	public static final String ROLES = "roles";
	public static final String ROLE = "role";
	public static final String ROLE_PREFIX = "ROLE_";

	public static final String TOKEN = "token";
	public static final String TOKEN_ID = "tokenId";
	public static final String REFRESH_TOKEN_ID = "refreshTokenId";
	public static final String AUTHENTICATION_ID = "authenticationId";

	public static final String HMAC_ALGORITHM = HmacAlgorithms.HMAC_SHA_512.getName();
}
