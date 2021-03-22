/**
 * 
 */
package com.co.app.commons.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "auth_user")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "nickname" }, doNotUseGetters = true, callSuper = false)
public class AuthUser extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "nickname")
	private String nickname;

	@NotNull
	@Column(name = "name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Email
	@Column(name = "email")
	private String email;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "authUser")
	private List<AuthUserRole> authUserRoleList = new ArrayList<>();

	@NotNull
	@Column(name = "password")
	private String password;

	@Column(name = "locked")
	private boolean locked;

	@Column(name = "enabled")
	private boolean enabled;

	@NotNull
	@Column(name = "phone")
	private String phone;

	public void addRole(String role) {
		if (this.authUserRoleList == null) {
			this.authUserRoleList = new ArrayList<>();
		}

		AuthUserRole authUserRole = new AuthUserRole();
		AuthUserRolePk authUserRolePk = new AuthUserRolePk();
		authUserRolePk.setNickname(nickname);
		authUserRolePk.setRoleId(role);
		authUserRole.setAuthUserRolePk(authUserRolePk);

		this.authUserRoleList.add(authUserRole);
	}

}
