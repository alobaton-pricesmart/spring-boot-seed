/**
 * 
 */
package com.innova4j.api.masters.domain;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.innova4j.api.commons.domain.BaseDomain;
import com.innova4j.api.masters.dto.MasterTypeDto;
import com.innova4j.api.message.MessageConstants;

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
	private String id;
	@NotNull
	@ElementCollection(targetClass = String.class)
	@MapKeyColumn(name = MessageConstants.LOCALE)
	private Map<String, String> name;
	@NotNull
	@ElementCollection(targetClass = String.class)
	@MapKeyColumn(name = MessageConstants.LOCALE)
	private Map<String, String> description;
	@ElementCollection(targetClass = String.class)
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
	 * @see com.innova4j.api.commons.domain.BaseDomain#toString()
	 */
	@Override
	public String toString() {
		return "MasterType [id=" + id + ", name=" + name + ", description=" + description + ", attributes=" + attributes
				+ ", created=" + created + ", lastModified=" + lastModified + "]";
	}

}
