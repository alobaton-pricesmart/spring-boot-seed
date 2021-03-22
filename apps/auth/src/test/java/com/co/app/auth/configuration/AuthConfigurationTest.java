/**
 * 
 */
package com.co.app.auth.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.app.auth.AuthApplication;

/**
 * @author alobaton
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AuthApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class AuthConfigurationTest {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	public void passwordEncoderTest() {
		System.out.println(bCryptPasswordEncoder.encode("admin"));
		System.out.println(bCryptPasswordEncoder.encode("api-secret"));
	}

}
