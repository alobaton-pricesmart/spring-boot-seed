package com.co.app.memory.dto;

import java.util.Map;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.co.app.memory.domain.Memory;
import com.co.app.commons.dto.BaseDto;

/**
 * @author luis.colmenarez
 *
 */
public class MemoryDto extends BaseDto {

	public static final Function<Memory, MemoryDto> CONVERTER = new Function<Memory, MemoryDto>() {
		@Override
		public MemoryDto apply(Memory t) {
			MemoryDto dto = new MemoryDto();
			dto.setKey(t.getId());
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
		return "MemoryDto [key=" + this.key + ", value=" + this.value + ", created=" + created + ", lastModified="
				+ lastModified + "]";
	}
}
