/**
 * 
 */
package com.co.app.auth.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.co.app.auth.domain.AuthUser;
import com.co.app.auth.domain.CustomUserDetails;
import com.co.app.auth.dto.AuthPasswordDto;
import com.co.app.auth.dto.AuthUserDto;
import com.co.app.auth.services.user.AuthUserService;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

	@Autowired
	private AuthUserService service;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/user-info")
	public @ResponseBody AuthUserDto userInfo(OAuth2Authentication authentication) {
		String nickname = null;
		if (authentication.getPrincipal() instanceof CustomUserDetails) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			nickname = userDetails.getUsername();
		} else if (authentication.getPrincipal() instanceof String) {
			nickname = (String) authentication.getPrincipal();
		}

		return AuthUserDto.CONVERTER.apply(service.get(nickname));
	}

	@GetMapping("/recovery-password")
	public void recoveryPassword(@Valid @RequestParam String nickname) {
		service.resetPassword(nickname);
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

		AuthUser user = service.get(nickname);
		user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));

		return AuthUserDto.CONVERTER.apply(service.update(user));
	}

	@PostMapping("/update-password/{id}")
	public @ResponseBody AuthUserDto resetPassword(@PathVariable @NotNull String id,
			@Valid @RequestBody AuthPasswordDto passwordDto) {
		AuthUser user = service.get(id);
		user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));

		return AuthUserDto.CONVERTER.apply(service.update(user));
	}
}
