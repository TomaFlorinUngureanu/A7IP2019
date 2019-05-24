package com.accountManagement.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.accountManagement.model.Users;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UsersRepositoryTest {

	private Users user;
	
	@Autowired
	UsersRepository repo;
	
	@BeforeEach
	void setUp() throws Exception {
		
		user = new Users();
		user.setEmail("blablabla@gmail.com");
		user.setPassword("qwerty");
		 repo.save(user);
	}
	
	@AfterEach
	void deleteData() throws Exception{
   		repo.deleteById("blablabla@gmail.com");
	}


	@Test
	@DisplayName("save in UsersRepository")
	void testSave() {
		 assertThat(repo.existsById("blablabla@gmail.com"),is(true));
		 assertNotNull(repo.findById("blablabla@gmail.com"));
		 assertEquals(repo.findById("blablabla@gmail.com").get().getPassword(),"qwerty");
	}
	
	@Test
	@DisplayName("exists by password test")
	void testExistsByPassword() {
		assertThat(repo.existsByPassword("qwerty"),is(true));
		repo.deleteById("blablabla@gmail.com");
		assertThat(repo.existsByPassword("qwerty"),is(false));
		repo.save(user);
	}

}
