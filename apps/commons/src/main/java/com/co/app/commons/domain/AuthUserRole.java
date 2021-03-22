package com.co.app.commons.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "auth_user_role")
@Data
@EqualsAndHashCode(of = { "authUserRolePk" }, doNotUseGetters = true, callSuper = false)
public class AuthUserRole extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AuthUserRolePk authUserRolePk;

	@JoinColumn(name = "nickname", referencedColumnName = "nickname")
	@ManyToOne(optional = false)
	@MapsId("nickname")
	private AuthUser authUser;

	@JoinColumn(name = "role_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@MapsId("roleId")
	private AuthRole authRole;

}
