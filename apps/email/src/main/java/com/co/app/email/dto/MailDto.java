/**
 * 
 */
package com.co.app.email.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author alobaton
 *
 */
public class MailDto {

	private String from;
	private List<String> to;
	private String subject;
	private List<String> attachments;
	private String text;
	private String template;
	private Map<String, Object> model;
	private Map<String, Object> properties;

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public List<String> getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(List<String> to) {
		this.to = to;
	}

	public void addTo(String to) {
		if (this.to == null) {
			this.to = new ArrayList<>();
		}

		this.to.add(to);
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the attachments
	 */
	public List<String> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * @return the model
	 */
	public Map<String, Object> getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	/**
	 * @return the properties
	 */
	public Map<String, Object> getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}
