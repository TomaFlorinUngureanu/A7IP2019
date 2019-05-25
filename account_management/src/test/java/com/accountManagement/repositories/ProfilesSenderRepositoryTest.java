package com.accountManagement.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.accountManagement.model.ProfilesSender;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProfilesSenderRepositoryTest {

	private ProfilesSender sender;
	
	@Autowired
	ProfilesSenderRepository repo;
	
	@BeforeEach
	void setUp() throws Exception {
		
		sender = new ProfilesSender();
		sender.setEmail("blablabla@gmail.com");
		sender.setName("Blablablu");
		sender.setPhone_number("0756435132");
		sender.setCountry("Romania");
		sender.setAddress1("aici");
		sender.setAddress2("acolo");
		sender.setAddress3("undeva");
		sender.setAddress4("altundeva");
		sender.setAddress5("nustiu unde");
		 repo.save(sender);

	}
	
	@AfterEach
	void deleteData() throws Exception{
   		repo.deleteById("blablabla@gmail.com");
	}

	@Test
	@DisplayName("save in SendersRepository")
	void testSave() {
		 assertThat(repo.existsById("blablabla@gmail.com"),is(true));
		 assertNotNull(repo.findById("blablabla@gmail.com"));
		 assertEquals(repo.findById("blablabla@gmail.com").get().getName(),"Blablablu");
		 assertEquals(repo.findById("blablabla@gmail.com").get().getCountry(),"Romania");
		 assertEquals(repo.findById("blablabla@gmail.com").get().getPhone_number(),"0756435132");
		 assertEquals(repo.findById("blablabla@gmail.com").get().getAddress1(),"aici");
		 assertEquals(repo.findById("blablabla@gmail.com").get().getAddress2(),"acolo");
		 assertEquals(repo.findById("blablabla@gmail.com").get().getAddress3(),"undeva");
		 assertEquals(repo.findById("blablabla@gmail.com").get().getAddress4(),"altundeva");
		 assertEquals(repo.findById("blablabla@gmail.com").get().getAddress5(),"nustiu unde");

	}

}
