package com.co.app.commons.repository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.co.app.commons.utils.BaseRepositoryUtil;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.StringPath;

public interface BaseRepository<T extends EntityPath<?>> extends QuerydslBinderCustomizer<T> {

	@SuppressWarnings("unchecked")
	@Override
	default void customize(QuerydslBindings bindings, T root) {
		Field[] fields = root.getClass().getDeclaredFields();
		for (Field f : fields) {
			Class<?> clazz = f.getType();
			Object field = null;
			try {
				field = f.get(root);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// Do nothing.
			}
			if (field != null && clazz == StringPath.class) {
				BaseRepositoryUtil.bindingsStringPath(bindings, (StringPath) field);
			} else if (field != null && clazz == DateTimePath.class) {
				BaseRepositoryUtil.bindingsDateTimePath(bindings, (DateTimePath<LocalDateTime>) field);
			}
		}
	}

}
