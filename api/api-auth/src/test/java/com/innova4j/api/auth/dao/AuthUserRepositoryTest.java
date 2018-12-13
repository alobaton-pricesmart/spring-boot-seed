/**
 * 
 */
package com.innova4j.api.auth.dao;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.innova4j.api.auth.configuration.AuthConfiguration;
import com.innova4j.api.auth.domain.AuthUser;

/**
 * @author innova4j-team
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AuthConfiguration.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
public class AuthUserRepositoryTest {

	@Autowired
	private AuthUserRepository repository;

	@Test
	public void createUser() {
		AuthUser user = new AuthUser();
		user.setNickname("test");
		user.setName("NAME");
		user.setEmail("test@domain.com");
		user.setPassword("****");
		user.addRole("test");

		repository.save(user);
	}

}
