package com.co.app.commons.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "code" }, doNotUseGetters = true, callSuper = false)
@MappedSuperclass
public class BaseTypeDomain extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code")
	private Long code;

	@Basic(optional = false)
	@Column(name = "status")
	private boolean status;

	@NotNull
	@Type(type = "json")
	@Column(name = "description", columnDefinition = "json")
	private Map<String, String> description = new HashMap<>();
}
