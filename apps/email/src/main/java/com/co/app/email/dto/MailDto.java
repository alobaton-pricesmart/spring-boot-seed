/**
 * 
 */
package com.co.app.email.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author alobaton
 *
 */
@Getter
@Setter
public class MailDto {

	private String from;
	private List<String> to;
	private String subject;
	private List<String> attachments;
	private String text;
	private String template;
	private Map<String, Object> model;
	private Map<String, Object> properties;

	public void addTo(String to) {
		if (this.to == null) {
			this.to = new ArrayList<>();
		}

		this.to.add(to);
	}

}
