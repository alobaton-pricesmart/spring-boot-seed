/**
 * 
 */
package com.alobaton.api.message.service.impl;

import java.util.Locale;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.alobaton.api.message.service.MessageService;

/**
 * @author alobaton
 *
 */
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageSource messageSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alobaton.api.message.service.MessageService#getMessage(java.lang.String)
	 */
	@Override
	public String getMessage(@NotNull String key) {
		return getMessage(key, Locale.getDefault());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alobaton.api.message.service.MessageService#getMessage(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public String getMessage(String key, Object[] args) {
		return getMessage(key, Locale.getDefault(), args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alobaton.api.message.service.MessageService#getMessage(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String getMessage(@NotNull String key, @NotNull Locale locale) {
		return getMessage(key, locale, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alobaton.api.message.service.MessageService#getMessage(java.lang.String,
	 * java.util.Locale, java.lang.Object[])
	 */
	@Override
	public String getMessage(@NotNull String key, @NotNull Locale locale, @Nullable Object[] args) {
		return messageSource.getMessage(key, args, locale);
	}

}
