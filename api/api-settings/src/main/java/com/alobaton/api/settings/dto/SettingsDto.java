/**
 * 
 */
package com.alobaton.api.settings.dto;

import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.alobaton.api.commons.dto.BaseDto;
import com.alobaton.api.settings.domain.Settings;

/**
 * @author alobaton
 *
 */
public class SettingsDto extends BaseDto {

	public static final Function<Settings, SettingsDto> CONVERTER = new Function<Settings, SettingsDto>() {
		@Override
		public SettingsDto apply(Settings t) {
			SettingsDto dto = new SettingsDto();
			dto.setId(t.getId());
			dto.setDescription(t.getDescription());
			dto.setValue(t.getValue());
			dto.setCreated(t.getCreated());
			dto.setLastModified(t.getLastModified());

			return dto;
		}
	};

	@NotNull
	private String id;
	private String description;
	@NotNull
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
		return "SettingsDto [id=" + id + ", description=" + description + ", value=" + value + ", created=" + created
				+ ", lastModified=" + lastModified + "]";
	}

}
