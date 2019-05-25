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

import com.accountManagement.model.ProfilesDriver;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProfilesDriverRepositoryTest {

	private ProfilesDriver driver;
	
	@Autowired
	ProfilesDriverRepository repo;
	
	@BeforeEach
	void setUp() throws Exception {
		
		driver = new ProfilesDriver();
		driver.setEmail("blablabla@gmail.com");
		driver.setName("Blablabli");
		driver.setCountry("Romania");
		driver.setPhone_number("0756743671");
		 repo.save(driver);

	}
	
	@AfterEach
	void deleteData() throws Exception{
   		repo.deleteById("blablabla@gmail.com");
	}

	@Test
	@DisplayName("save in DriversRepository")
	void testSave() {
		 assertThat(repo.existsById("blablabla@gmail.com"),is(true));
		 assertNotNull(repo.findById("blablabla@gmail.com"));
		 assertEquals(repo.findById("blablabla@gmail.com").get().getName(),"Blablabli");
		 assertEquals(repo.findById("blablabla@gmail.com").get().getCountry(),"Romania");
		 assertEquals(repo.findById("blablabla@gmail.com").get().getPhone_number(),"0756743671");
	}
	

}
