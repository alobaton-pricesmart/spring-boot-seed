package com.co.app.cache.domain;

import java.util.Map;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.co.app.cache.dto.CacheDto;
import com.co.app.commons.domain.BaseDomain;

/**
 * @author luis.colmenarez
 *
 */
@Entity
@Table(name = "cache")
public class Cache extends BaseDomain {
	
	public static final Function<CacheDto, Cache> CONVERTER = new Function<CacheDto, Cache>() {
		@Override
		public Cache apply(CacheDto t) {
			Cache domain = new Cache();
			domain.setKey(t.getKey());
			domain.setValue(t.getValue());
			return domain;
		}
	};
	
	@Id
	@NotNull
	@Column(name = "key")
	private String key;

	@NotNull
	@Type(type = "json")
	@Column(name = "value", columnDefinition = "json")
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
		return "Cache [key=" + this.key + ", value=" + this.value
				+ ", created=" + created + ", lastModified=" + lastModified + "]";
	}
}
