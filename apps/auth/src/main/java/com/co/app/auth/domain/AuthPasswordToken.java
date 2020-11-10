/**
 * 
 */
package com.co.app.auth.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.co.app.commons.domain.BaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "auth_password_token")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "id" }, doNotUseGetters = true, callSuper = false)
public class AuthPasswordToken extends BaseEntity {

	@EmbeddedId
	private AuthPasswordTokenId id;

	@NotNull
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "expires_at")
	private LocalDateTime expiresAt;

	public boolean isExpired() {
		LocalDateTime now = LocalDateTime.now();
		return this.expiresAt == null || this.expiresAt.isBefore(now);
	}

}
