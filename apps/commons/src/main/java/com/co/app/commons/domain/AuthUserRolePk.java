package com.co.app.commons.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Embeddable
@Data
@EqualsAndHashCode(of = { "nickname", "roleId" }, doNotUseGetters = true)
public class AuthUserRolePk implements Serializable {

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "nickname")
	private String nickname;

	@Basic(optional = false)
	@Column(name = "role_id")
	private String roleId;

}
