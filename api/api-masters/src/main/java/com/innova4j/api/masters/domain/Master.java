/**
 * 
 */
package com.innova4j.api.masters.domain;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.validation.constraints.NotNull;

import com.innova4j.api.commons.domain.BaseDomain;
import com.innova4j.api.masters.dto.MasterDto;
import com.innova4j.api.message.MessageConstants;

/**
 * @author innova4j-team
 *
 */
public class Master extends BaseDomain {

	public static final Function<MasterDto, Master> CONVERTER = new Function<MasterDto, Master>() {
		@Override
		public Master apply(MasterDto t) {
			Master domain = new Master();

			return domain;
		}
	};

	@Id
	@NotNull
	@GeneratedValue
	private String id;
	@NotNull
	@ElementCollection(targetClass = String.class)
	@MapKeyColumn(name = MessageConstants.LOCALE)
	private Map<String, String> name;
	@NotNull
	@ElementCollection(targetClass = String.class)
	private Set<String> masterTypes;
	@ElementCollection(targetClass = String.class)
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
