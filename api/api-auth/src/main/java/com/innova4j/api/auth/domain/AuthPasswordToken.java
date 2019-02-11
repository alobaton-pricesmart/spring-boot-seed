/**
 * 
 */
package com.innova4j.api.auth.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.innova4j.api.commons.domain.BaseEntity;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "auth_password_token")
public class AuthPasswordToken extends BaseEntity {

	@EmbeddedId
	private AuthPasswordTokenId id;

	@NotNull
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "expires_at")
	private LocalDateTime expiresAt;

	/**
	 * @return the id
	 */
	public AuthPasswordTokenId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(AuthPasswordTokenId id) {
		this.id = id;
	}

	/**
	 * @return the expiresAt
	 */
	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	/**
	 * @param expiresAt the expiresAt to set
	 */
	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public boolean isExpired() {
		LocalDateTime now = LocalDateTime.now();
		return this.expiresAt == null || this.expiresAt.isBefore(now);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthPasswordToken [id=" + id + ", expiresAt=" + expiresAt + "]";
	}

}
