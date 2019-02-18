/**
 * 
 */
package com.alobaton.api.masters.dto;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.alobaton.api.commons.dto.BaseDto;
import com.alobaton.api.masters.domain.MasterType;

/**
 * @author alobaton
 *
 */
public class MasterTypeDto extends BaseDto {

	public static final Function<MasterType, MasterTypeDto> CONVERTER = new Function<MasterType, MasterTypeDto>() {
		@Override
		public MasterTypeDto apply(MasterType t) {
			MasterTypeDto dto = new MasterTypeDto();
			dto.setId(t.getId());
			dto.setName(t.getName());
			dto.setDescription(t.getDescription());
			dto.setAttributes(t.getAttributes());
			dto.setCreated(t.getCreated());
			dto.setLastModified(t.getLastModified());

			return dto;
		}
	};

	@NotNull
	private String id;
	@NotNull
	private Map<String, String> name;
	@NotNull
	private Map<String, String> description;
	private Set<String> attributes;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public Map<String, String> getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(Map<String, String> name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public Map<String, String> getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(Map<String, String> description) {
		this.description = description;
	}

	/**
	 * @return the attributes
	 */
	public Set<String> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Set<String> attributes) {
		this.attributes = attributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MasterTypeDto [id=" + id + ", name=" + name + ", description=" + description + ", attributes="
				+ attributes + ", created=" + created + ", lastModified=" + lastModified + "]";
	}

}
