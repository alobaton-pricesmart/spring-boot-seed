/**
 * 
 */
package com.co.app.auth.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.data.domain.Example;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.co.app.auth.configuration.AuthConfigurationTest;
import com.co.app.auth.dao.AuthUserRepository;
import com.co.app.auth.domain.AuthUser;

/**
 * @author alobaton
 *
 */
@RunWith(SpringRunner.class)
@AutoConfigureDataJpa
@ContextConfiguration(classes = { AuthConfigurationTest.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback
public class AuthUserRepositoryTest {

	@Autowired
	private AuthUserRepository repository;

	@Before
	public void setUp() {
		AuthUser user = new AuthUser();
		user.setNickname("setup");
		user.setName("SETUP");
		user.setLastName("SETUP");
		user.setEmail("setup@domain.com");
		user.setPassword("****");
		user.addRole("USER");

		repository.save(user);
	}

	@Test
	public void testGet() {
		Assert.assertNotNull(repository.findById("setup"));
	}

	@Test
	public void testFindOneIsPresent() {
		AuthUser user = new AuthUser();
		user.setName("SETUP");

		Example<AuthUser> example = Example.of(user);

		Assert.assertTrue(repository.findOne(example).isPresent());
	}

	@Test
	public void testFindOneNotPresent() {
		AuthUser user = new AuthUser();
		user.setName("USER");

		Example<AuthUser> example = Example.of(user);

		Assert.assertFalse(repository.findOne(example).isPresent());
	}

	@Test
	public void testFindAll() {
		List<AuthUser> expected = new ArrayList<>();

		AuthUser other = new AuthUser();
		other.setNickname("other00");
		other.setName("OTHER");
		other.setLastName("OTHER");
		other.setEmail("other.00@domain.com");
		other.setPassword("****");
		other.addRole("USER");

		expected.add(repository.save(other));

		other = new AuthUser();
		other.setNickname("other01");
		other.setName("OTHER");
		other.setLastName("OTHER");
		other.setEmail("other.01@domain.com");
		other.setPassword("****");
		other.addRole("USER");

		expected.add(repository.save(other));

		AuthUser user = new AuthUser();
		user.setName("OTHER");
		Example<AuthUser> example = Example.of(user);

		List<AuthUser> actuals = repository.findAll(example);

		Assert.assertArrayEquals(expected.toArray(), actuals.toArray());
	}
}
