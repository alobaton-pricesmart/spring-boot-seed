/**
 * 
 */
package com.co.app.settings.domain;

import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.co.app.commons.domain.BaseDomain;
import com.co.app.settings.dto.SettingsDto;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "settings")
public class Settings extends BaseDomain {

	public static final Function<SettingsDto, Settings> CONVERTER = new Function<SettingsDto, Settings>() {
		@Override
		public Settings apply(SettingsDto t) {
			Settings domain = new Settings();
			domain.setId(t.getId());
			domain.setDescription(t.getDescription());
			domain.setValue(t.getValue());

			return domain;
		}
	};

	@Id
	@NotNull
	@GeneratedValue
	@Column(name = "id")
	private String id;

	@Column(name = "description")
	private String description;

	@NotNull
	@Column(name = "value")
	private String value;

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Settings [id=" + id + ", description=" + description + ", value=" + value + ", created=" + created
				+ ", lastModified=" + lastModified + "]";
	}

}
