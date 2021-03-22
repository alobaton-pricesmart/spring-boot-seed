package com.co.app.commons.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class MessageDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String timestamp;
	private int status;
	private String message;
	private String responseCode;
	private String description;
	private String path;

}
