package com.co.app.commons.converter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate localDate) {
		return Optional.ofNullable(localDate).map(Date::valueOf).orElse(null);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date date) {
		return Optional.ofNullable(date).map(d -> {
			LocalDate localDate = d.toLocalDate();
			return localDate.plusDays(1);
		}).orElse(null);
	}
}