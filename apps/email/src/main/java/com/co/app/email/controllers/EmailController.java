/**
 * 
 */
package com.co.app.email.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.email.dto.MailDto;
import com.co.app.email.service.EmailService;
import com.co.app.email.utils.EmailServiceFactoryObject;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailServiceFactoryObject emailServiceFactoryObject;

	@PostMapping("/{impl}")
	public void sendMail(@RequestBody MailDto mail, @PathVariable("impl") String impl) {
		EmailService service = emailServiceFactoryObject.emailService(impl);
		service.sendEmail(mail);
	}

}
