/**
 * 
 */
package com.innova4j.api.auth.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.innova4j.api.auth.configuration.AuthConfigurationTest;
import com.innova4j.api.auth.domain.AuthUser;

/**
 * @author innova4j-team
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = { AuthConfigurationTest.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback
public class AuthUserRepositoryTest {

	@Autowired
	private AuthUserRepository repository;

	@Before
	public void setup() {
		AuthUser user = new AuthUser();
		user.setNickname("setup");
		user.setName("SETUP");
		user.setEmail("setup@domain.com");
		user.setPassword("****");
		user.addRole("ROLE_SETUP");

		repository.save(user);
	}

	@After
	public void clear() {
		repository.deleteAll();
	}

	@Test
	public void create() {
		AuthUser user = new AuthUser();
		user.setNickname("test");
		user.setName("NAME");
		user.setEmail("test@domain.com");
		user.setPassword("****");
		user.addRole("ROLE_TEST");

		Assert.assertNotNull(repository.save(user));
	}

	@Test
	public void get() {
		Assert.assertNotNull(repository.getOne("setup"));
	}

	@Test
	public void customGet() {
		AuthUser user = new AuthUser();
		user.setNickname("setup");
		user.setName("SETUP");

		Example<AuthUser> example = Example.of(user);

		Assert.assertTrue(repository.findOne(example).isPresent());

		user = new AuthUser();
		user.setNickname("other");
		user.setName("OTHER");

		example = Example.of(user);

		Assert.assertFalse(repository.findOne(example).isPresent());
	}

	@Test
	public void customGetAll() {
		List<AuthUser> expected = new ArrayList<>();

		AuthUser other = new AuthUser();
		other.setNickname("other00");
		other.setName("OTHER");
		other.setEmail("other.00@domain.com");
		other.setPassword("****");
		other.addRole("ROLE_OTHER");

		expected.add(repository.save(other));

		other = new AuthUser();
		other.setNickname("other01");
		other.setName("OTHER");
		other.setEmail("other.01@domain.com");
		other.setPassword("****");
		other.addRole("ROLE_OTHER");

		expected.add(repository.save(other));

		AuthUser user = new AuthUser();
		user.setName("OTHER");

		Example<AuthUser> example = Example.of(user);

		List<AuthUser> actuals = repository.findAll(example);

		System.out.println(StringUtils.join(actuals));

		Assert.assertArrayEquals(expected.toArray(), actuals.toArray());
	}

}
