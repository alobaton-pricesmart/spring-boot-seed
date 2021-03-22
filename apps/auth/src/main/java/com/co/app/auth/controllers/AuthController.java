/**
 * 
 */
package com.co.app.auth.controllers;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.auth.constants.AuthConstants;
import com.co.app.auth.domain.CustomUserDetails;
import com.co.app.auth.services.AuthUserService;
import com.co.app.auth.utils.LoggedUserUtil;
import com.co.app.commons.domain.AuthRole;
import com.co.app.commons.domain.AuthUser;
import com.co.app.commons.domain.AuthUserRole;
import com.co.app.commons.domain.AuthUserRolePk;
import com.co.app.commons.dto.AuthPasswordDto;
import com.co.app.commons.dto.AuthUserDto;
import com.co.app.commons.dto.AuthUserRegisterDto;
import com.co.app.commons.exception.ApiException;
import com.co.app.commons.utils.StringUtil;
import com.co.app.message.constants.MessageConstants;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

	private static final Log LOGGER = LogFactory.getLog(AuthController.class);

	@Autowired
	private AuthUserService authUserService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ConsumerTokenServices tokenServices;

	@Autowired
	private TokenEndpoint tokenEndpoint;

	@DeleteMapping("/logout")
	public void revokeToken() {
		String token = LoggedUserUtil.getToken();
		if (token != null && !token.isEmpty()) {
			tokenServices.revokeToken(token);
		}
	}

	@GetMapping("/user-info")
	public @ResponseBody AuthUserDto userInfo(OAuth2Authentication authentication) {
		String nickname = null;
		if (authentication.getPrincipal() instanceof CustomUserDetails) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			nickname = userDetails.getUsername();
		} else if (authentication.getPrincipal() instanceof String) {
			nickname = (String) authentication.getPrincipal();
		}

		return AuthUserDto.CONVERTER_DTO.apply(authUserService.get(nickname));
	}

	@PostMapping("/recovery-password")
	public void recoveryPassword(@RequestBody Map<String, String> body) {
		String email = body.get("email");
		authUserService.recoveryPassword(email);
	}

	@PostMapping("/reset-password")
	public @ResponseBody AuthUserDto resetPassword(OAuth2Authentication authentication,
			@Valid @RequestBody AuthPasswordDto passwordDto) {
		// The authentication is seted in the PasswordGranter.
		String nickname = null;
		if (authentication.getPrincipal() instanceof CustomUserDetails) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			nickname = userDetails.getUsername();
		} else if (authentication.getPrincipal() instanceof String) {
			nickname = (String) authentication.getPrincipal();
		}

		AuthUser user = authUserService.get(nickname);
		user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));

		return AuthUserDto.CONVERTER_DTO.apply(authUserService.update(user));
	}

	@PostMapping("/register")
	@Transactional
	public @ResponseBody AuthUserRegisterDto register(HttpServletRequest request, Principal principal,
			OAuth2Authentication authentication, @Valid @RequestBody AuthUserRegisterDto dto) {
		AuthUser user = AuthUserRegisterDto.CONVERTER_ENTITY.apply(dto);

		// Extract nickname
		String email = dto.getEmail();
		int endIndex = email.indexOf(StringUtil.AT_SIGN);
		String nickname = dto.getEmail().substring(0, endIndex).replaceAll("\\.+$", StringUtil.EMPTY);
		user.setNickname(nickname);

		// Encode password
		user.setPassword(passwordEncoder.encode(dto.getPassword()));

		// Setup default role...
		AuthRole authRole = new AuthRole();
		authRole.setId(AuthConstants.DEFAULT_ROLE);
		AuthUserRole authUserRole = new AuthUserRole();
		AuthUserRolePk authUserRolePk = new AuthUserRolePk();
		authUserRolePk.setNickname(nickname);
		authUserRolePk.setRoleId(AuthConstants.DEFAULT_ROLE);
		authUserRole.setAuthUserRolePk(authUserRolePk);
		authUserRole.setAuthRole(authRole);
		authUserRole.setAuthUser(user);
		user.setAuthUserRoleList(Arrays.asList(authUserRole));

		// Enabled by default.
		user.setEnabled(true);
		user.setLocked(false);
		authUserService.createAndFlush(user);

		// Make auto login
		HashMap<String, String> requestParameters = new HashMap<>();
		requestParameters.put(AuthConstants.USERNAME, user.getNickname());
		requestParameters.put(AuthConstants.PASSWORD, dto.getPassword());
		requestParameters.put(OAuth2Utils.GRANT_TYPE, AuthConstants.PASSWORD);

		ResponseEntity<OAuth2AccessToken> responseEntityOAuth2AccessToken = null;
		try {
			responseEntityOAuth2AccessToken = tokenEndpoint.postAccessToken(principal, requestParameters);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		if (null == responseEntityOAuth2AccessToken || null == responseEntityOAuth2AccessToken.getBody()) {
			throw new ApiException(MessageConstants.ERROR_GENERAL_ERROR);
		}

		OAuth2AccessToken oAuth2AccessToken = responseEntityOAuth2AccessToken.getBody();
		dto.setToken(oAuth2AccessToken);
		return dto;
	}
}
