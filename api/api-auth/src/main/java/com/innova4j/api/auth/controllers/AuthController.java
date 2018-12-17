/**
 * 
 */
package com.innova4j.api.auth.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.innova4j.api.auth.domain.CustomUserDetails;
import com.innova4j.api.auth.dto.AuthPasswordDto;
import com.innova4j.api.auth.dto.AuthUserDto;
import com.innova4j.api.auth.services.user.AuthUserService;

/**
 * @author innova4j-team
 *
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

	@Autowired
	private AuthUserService service;

	@Autowired
	private BCryptPasswordEncoder encoder;

	/**
	 * Returns user information from the token.
	 * 
	 * @param token The token.
	 * @return The user information.
	 */
	@GetMapping("/user-info")
	public @ResponseBody AuthUserDto userInfo(OAuth2Authentication authentication) {
		String nickname = null;
		if (authentication.getPrincipal() instanceof CustomUserDetails) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			nickname = userDetails.getUsername();
		} else if (authentication.getPrincipal() instanceof String) {
			nickname = (String) authentication.getPrincipal();
		}

		return service.get(nickname);
	}

	/**
	 * Recovery password.
	 * 
	 * @param nicknme The nicknme.
	 * @return
	 */
	@GetMapping("/recovery-password")
	public void recoveryPassword(@Valid @RequestParam String nickname) {
		service.resetPassword(nickname);
	}

	/**
	 * Reset the password.
	 * 
	 * @param authentication
	 * @param passwordDto
	 * @return
	 */
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

		AuthUserDto user = service.get(nickname);
		user.setPassword(encoder.encode(passwordDto.getPassword()));

		return service.update(user);
	}

	/**
	 * 
	 * Update user password.
	 * 
	 * @param id
	 * @param passwordDto
	 * @return
	 */
	@PostMapping("/update-password/{id}")
	@PreAuthorize("")
	public @ResponseBody AuthUserDto resetPassword(@PathVariable @NotNull String id,
			@Valid @RequestBody AuthPasswordDto passwordDto) {
		AuthUserDto user = service.get(id);
		user.setPassword(encoder.encode(passwordDto.getPassword()));

		return service.update(user);
	}
}
