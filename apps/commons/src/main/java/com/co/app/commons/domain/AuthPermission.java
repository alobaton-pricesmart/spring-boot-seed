/**
 * 
 */
package com.co.app.commons.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "auth_permission")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "id" }, doNotUseGetters = true, callSuper = false)
public class AuthPermission extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@Type(type = "json")
	@Column(name = "description", columnDefinition = "json")
	private Map<String, String> description = new HashMap<>();

}
