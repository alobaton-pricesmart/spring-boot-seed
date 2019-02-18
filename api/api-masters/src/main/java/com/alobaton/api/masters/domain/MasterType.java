/**
 * 
 */
package com.alobaton.api.masters.domain;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.alobaton.api.commons.domain.BaseDomain;
import com.alobaton.api.masters.dto.MasterTypeDto;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "master_type")
public class MasterType extends BaseDomain {

	public static final Function<MasterTypeDto, MasterType> CONVERTER = new Function<MasterTypeDto, MasterType>() {
		@Override
		public MasterType apply(MasterTypeDto t) {
			MasterType domain = new MasterType();
			domain.setId(t.getId());
			domain.setName(t.getName());
			domain.setDescription(t.getDescription());
			domain.setAttributes(t.getAttributes());

			return domain;
		}
	};

	@Id
	@NotNull
	@GeneratedValue
	@Column(name = "id")
	private String id;

	@NotNull
	@Type(type = "json")
	@Column(name = "name", columnDefinition = "json")
	private Map<String, String> name;

	@NotNull
	@Type(type = "json")
	@Column(name = "description", columnDefinition = "json")
	private Map<String, String> description;

	@Type(type = "json")
	@Column(name = "attributes", columnDefinition = "json")
	private Set<String> attributes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, String> getName() {
		return name;
	}

	public void setName(Map<String, String> name) {
		this.name = name;
	}

	public Map<String, String> getDescription() {
		return description;
	}

	public void setDescription(Map<String, String> description) {
		this.description = description;
	}

	public Set<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<String> attributes) {
		this.attributes = attributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alobaton.api.commons.domain.BaseDomain#toString()
	 */
	@Override
	public String toString() {
		return "MasterType [id=" + id + ", name=" + name + ", description=" + description + ", attributes=" + attributes
				+ ", created=" + created + ", lastModified=" + lastModified + "]";
	}

}
