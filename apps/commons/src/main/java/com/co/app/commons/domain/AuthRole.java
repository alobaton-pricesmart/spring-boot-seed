/**
 * 
 */
package com.co.app.commons.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "auth_role")
@Data
@EqualsAndHashCode(of = { "id" }, doNotUseGetters = true, callSuper = false)
public class AuthRole extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "id")
	private String id;

	// LDAP groupId mapping.
	@Column(name = "group_id")
	private String groupId;

	@NotNull
	@Type(type = "json")
	@Column(name = "description", columnDefinition = "json")
	private Map<String, String> description = new HashMap<>();

	@Column(name = "parent_id")
	private String parentId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "authRole")
	private List<AuthRolePermission> authRolePermissionList = new ArrayList<>();

}
