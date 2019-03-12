package com.co.app.cache.dto;

import java.util.Map;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.co.app.cache.domain.Cache;
import com.co.app.commons.dto.BaseDto;

/**
 * @author luis.colmenarez
 *
 */
public class CacheDto extends BaseDto {

	public static final Function<Cache, CacheDto> CONVERTER = new Function<Cache, CacheDto>() {
		@Override
		public CacheDto apply(Cache t) {
			CacheDto dto = new CacheDto();
			dto.setKey(t.getKey());
			dto.setValue(t.getValue());
			dto.setCreated(t.getCreated());
			dto.setLastModified(t.getLastModified());

			return dto;
		}
	};

	@NotNull
	private String key;
	@NotNull
	private Map<String, Object> value;

	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public Map<String, Object> getValue() {
		return value;
	}


	public void setValue(Map<String, Object> value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return "CacheDto [key=" + this.key + ", value=" + this.value
				+ ", created=" + created + ", lastModified=" + lastModified + "]";
	}
}
