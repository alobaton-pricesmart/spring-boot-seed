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
@Table(name = "auth_role_permission")
@Data
@EqualsAndHashCode(of = { "authRolePermissionPk" }, doNotUseGetters = true, callSuper = false)
public class AuthRolePermission extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AuthRolePermissionPk authRolePermissionPk;

	@JoinColumn(name = "role_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@MapsId("roleId")
	private AuthRole authRole;

	@JoinColumn(name = "permission_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@MapsId("permissionId")
	private AuthPermission authPermission;

}
