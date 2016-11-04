package com.spc.jpa.domain.user;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import com.spc.jpa.AbstractIntegrationTest;

/**
 * @author lKJ
 */
@Transactional
@Rollback
public class UserRepositoryTest extends AbstractIntegrationTest{

	@Inject
	UserRepository userRepository;

	@Test
	@Rollback(false)
	public void testUser() throws Exception {

		String id = "bliex";

		// findAll
		List<User> list = userRepository.findAll();
		assertNotNull(list);		


		User user = new User();
		user.setId(id);
		user.setPassword("qwer1234");
		user.setName("테스트");

		// insert
		User savedUser = userRepository.save(user);
		assertEquals("qwer1234", savedUser.getPassword());


		// update
		user.setPassword("qwer1234");
		user.setName("테스트");
		savedUser = userRepository.save(user);
		assertEquals("qwer1234", savedUser.getPassword());

//		// delete
//		userRepository.delete(user.getId());
		
	}
}
