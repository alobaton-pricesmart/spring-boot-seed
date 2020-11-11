/**
 * 
 */
package com.co.app.auth.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.co.app.auth.dto.AuthRoleDto;
import com.co.app.commons.domain.BaseDomain;

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

	@Type(type = "json")
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<AuthPermission> permissions = new ArrayList<>();

	public static final Function<AuthRoleDto, AuthRole> CONVERTER = (AuthRoleDto t) -> {
		AuthRole domain = new AuthRole();
		domain.setId(t.getId());
		domain.setDescription(t.getDescription());
		domain.setGroupId(t.getGroupId());
		domain.setParentId(t.getParentId());
		domain.setPermissions(
				t.getPermissions().stream().map(AuthPermission.CONVERTER).collect(Collectors.<AuthPermission>toList()));

		return domain;
	};

}
