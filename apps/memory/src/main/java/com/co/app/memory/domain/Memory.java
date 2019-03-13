package com.co.app.memory.domain;

import java.util.Map;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.co.app.memory.dto.MemoryDto;
import com.co.app.commons.domain.BaseDomain;

/**
 * @author luis.colmenarez
 *
 */
@Entity
@Table(name = "distributed_memory")
public class Memory extends BaseDomain {

	public static final Function<MemoryDto, Memory> CONVERTER = new Function<MemoryDto, Memory>() {
		@Override
		public Memory apply(MemoryDto t) {
			Memory domain = new Memory();
			domain.setId(t.getKey());
			domain.setValue(t.getValue());

			return domain;
		}
	};

	@Id
	@NotNull
	@Column(name = "id")
	private String id;

	@NotNull
	@Type(type = "json")
	@Column(name = "value", columnDefinition = "json")
	private Map<String, Object> value;

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

	public Map<String, Object> getValue() {
		return value;
	}

	public void setValue(Map<String, Object> value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Memory [key=" + this.key + ", value=" + this.value + ", created=" + created + ", lastModified="
				+ lastModified + "]";
	}
}
