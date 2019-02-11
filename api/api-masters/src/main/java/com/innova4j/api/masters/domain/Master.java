/**
 * 
 */
package com.innova4j.api.masters.domain;

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

import com.innova4j.api.commons.domain.BaseDomain;
import com.innova4j.api.masters.dto.MasterDto;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "master")
public class Master extends BaseDomain {

	public static final Function<MasterDto, Master> CONVERTER = new Function<MasterDto, Master>() {
		@Override
		public Master apply(MasterDto t) {
			Master domain = new Master();
			domain.setId(t.getId());
			domain.setName(t.getName());
			domain.setMasterTypes(t.getMasterTypes());
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
	@Column(name = "master_types", columnDefinition = "json")
	private Set<String> masterTypes;

	@Type(type = "json")
	@Column(name = "attributes", columnDefinition = "json")
	private Map<String, Object> attributes;

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

	public Set<String> getMasterTypes() {
		return masterTypes;
	}

	public void setMasterTypes(Set<String> masterTypes) {
		this.masterTypes = masterTypes;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innova4j.api.commons.domain.BaseDomain#toString()
	 */
	@Override
	public String toString() {
		return "Master [id=" + id + ", name=" + name + ", masterTypes=" + masterTypes + ", attributes=" + attributes
				+ ", created=" + created + ", lastModified=" + lastModified + "]";
	}

}
