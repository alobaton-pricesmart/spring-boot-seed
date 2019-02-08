/**
 * 
 */
package com.innova4j.api.masters.dto;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.innova4j.api.commons.dto.BaseDto;
import com.innova4j.api.masters.domain.Master;

/**
 * @author alobaton
 *
 */
public class MasterDto extends BaseDto {

	public static final Function<Master, MasterDto> CONVERTER = new Function<Master, MasterDto>() {
		@Override
		public MasterDto apply(Master t) {
			MasterDto dto = new MasterDto();
			dto.setId(t.getId());
			dto.setName(t.getName());
			dto.setMasterTypes(t.getMasterTypes());
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
	private Set<String> masterTypes;
	private Map<String, Object> attributes;

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
	 * @return the masterTypes
	 */
	public Set<String> getMasterTypes() {
		return masterTypes;
	}

	/**
	 * @param masterTypes the masterTypes to set
	 */
	public void setMasterTypes(Set<String> masterTypes) {
		this.masterTypes = masterTypes;
	}

	/**
	 * @return the attributes
	 */
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MasterDto [id=" + id + ", name=" + name + ", masterTypes=" + masterTypes + ", attributes=" + attributes
				+ ", created=" + created + ", lastModified=" + lastModified + "]";
	}

}
