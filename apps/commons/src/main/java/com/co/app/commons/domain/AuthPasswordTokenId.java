/**
 * 
 */
package com.co.app.commons.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alobaton
 *
 */
@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "nickname", "token" }, doNotUseGetters = true, callSuper = false)
public class AuthPasswordTokenId implements Serializable {

	private static final long serialVersionUID = 3076852464331727336L;

	@NotNull
	@Column(name = "nickname")
	private String nickname;

	@NotNull
	@Column(name = "token")
	private String token;

}
