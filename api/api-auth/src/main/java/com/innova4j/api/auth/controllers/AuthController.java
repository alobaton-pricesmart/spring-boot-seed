/**
 * 
 */
package com.innova4j.api.auth.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.innova4j.api.auth.domain.AuthUser;
import com.innova4j.api.auth.dto.AuthPasswordDto;
import com.innova4j.api.auth.services.user.AuthUserService;

/**
 * @author innova4j-team
 *
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

	@Autowired
	private AuthUserService userService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	/**
	 * Returns user information from the token.
	 * 
	 * @param token The token.
	 * @return The user information.
	 */
	@GetMapping("/userinfo")
	public @ResponseBody AuthUser userInfo(OAuth2Authentication authentication) {
		final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();

		String accessToken = details.getTokenValue();

		return null;

	}

	/**
	 * Reset password.
	 * 
	 * @param nicknme The nicknme.
	 * @return
	 */
	@GetMapping("/recoverypassword")
	public void recoveryPassword(@Valid @RequestParam String nickname) {
	}

	/**
	 * Reset the password.
	 * 
	 * @param authentication
	 * @param passwordDto
	 * @return
	 */
	@PostMapping("/resetpassword")
	public @ResponseBody Map<String, String> resetPassword(OAuth2Authentication authentication,
			@Valid @RequestBody AuthPasswordDto passwordDto) {
		final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();

		String accessToken = details.getTokenValue();

		Map<String, String> response = new HashMap<String, String>();
		return response;

	}

	/**
	 * 
	 * Update user password.
	 * 
	 * @param userId
	 * @param passwordDto
	 * @return
	 */
	@PostMapping("/updatepassword/{userId}")
	@PreAuthorize("")
	public @ResponseBody AuthUser resetPassword(@PathVariable @NotNull String userId,
			@Valid @RequestBody AuthPasswordDto passwordDto) {
		AuthUser user = userService.get(userId);
		user.setPassword(encoder.encode(passwordDto.getPassword()));

		return userService.update(user);
	}
}
