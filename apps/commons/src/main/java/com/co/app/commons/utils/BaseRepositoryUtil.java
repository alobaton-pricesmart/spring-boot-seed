package com.co.app.commons.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.StringPath;

public class BaseRepositoryUtil {

	private static final String EQUAL = "eq";
	private static final String LIKE = "like";
	private static final String IN = "in";

	private BaseRepositoryUtil() {
	}

	public static final BooleanExpression andExpression(BooleanExpression exp01, BooleanExpression exp02) {
		if (exp01 == null) {
			exp01 = exp02;
		}
		exp01.and(exp02);
		return exp01;
	}

	public static final String like(String s) {
		return String.format("%%%s%%", s);
	}

	public static final void bindingsStringPath(QuerydslBindings bindings, StringPath stringPath) {
		bindings.bind(stringPath).all((path, v) -> {
			List<? extends String> values = new ArrayList<>(v);
			BooleanExpression expression = null;
			for (String value : values) {
				String[] query = value.split(StringUtil.COMMA);
				if (query.length == 1) {
					String val = query[0];
					expression = BaseRepositoryUtil.andExpression(expression, path.eq(val));
				} else if (query.length == 2) {
					String op = query[1];
					String val = query[0];
					if (EQUAL.equals(op)) {
						expression = BaseRepositoryUtil.andExpression(expression, path.eq(val));
					} else if (LIKE.equals(op)) {
						expression = BaseRepositoryUtil.andExpression(expression,
								path.likeIgnoreCase(BaseRepositoryUtil.like(val)));
					} else if (IN.equals(op)) {
						expression = BaseRepositoryUtil.andExpression(expression,
								path.in(Arrays.asList(val.split(StringUtil.SEMICOLON))));
					} else {
						expression = BaseRepositoryUtil.andExpression(expression, path.eq(val));
					}
				}
			}
			return Optional.of(expression);
		});
	}

	public static final void bindingsDateTimePath(QuerydslBindings bindings, DateTimePath<LocalDateTime> dateTimePath) {
		bindings.bind(dateTimePath).all((path, v) -> {
			List<? extends LocalDateTime> dates = new ArrayList<>(v);
			if (dates.size() == 1) {
				return Optional.of(path.eq(dates.get(0)));
			} else {
				LocalDateTime from = dates.get(0);
				LocalDateTime to = dates.get(1);
				return Optional.of(path.between(from, to));
			}
		});
	}
}
