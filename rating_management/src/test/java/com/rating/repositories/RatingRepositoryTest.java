package com.rating.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rating.model.RatingForm;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RatingRepositoryTest {

	private RatingForm rating;
	private int id;
	
	@Autowired
	RatingRepository repo;
	
	@BeforeEach
	void setUp() throws Exception {
		
		rating = new RatingForm();
		rating.setEmailDriver("thebestdriverever@gmail.com");
		rating.setIdPackage(1);
		rating.setRating(10);
		
		repo.save(rating);
       
		id = repo.findAll().get(repo.findAll().size()-1).getId();
	}
	
	@AfterEach
	void deleteData() {
		repo.deleteById(id);
	}

	@Test
	@DisplayName("saveData test")
	void testSave() {
		assertThat(repo.existsById(id),is(true));
		assertThat(repo.existsByEmailDriver("thebestdriverever@gmail.com"),is(true));
		assertThat(repo.existsByIdPackage(1),is(true));
		repo.deleteById(id);
		assertThat(repo.existsById(id),is(false));
		assertThat(repo.existsByEmailDriver("thebestdriverever@gmail.com"),is(false));
		assertThat(repo.existsByIdPackage(1),is(false));
		repo.save(rating);
		id = repo.findAll().get(repo.findAll().size()-1).getId();
	}
	
	@Test
	@DisplayName("find all by driver's email test")
	void testFindAllByEmailDriver() {
		
		int anotherId = 0;
		RatingForm anotherRating = new RatingForm();
		anotherRating.setEmailDriver("thebestdriverever@gmail.com");
		anotherRating.setIdPackage(2);
		anotherRating.setRating(10);
		
		repo.save(anotherRating);
		
		anotherId = repo.findAll().get(repo.findAll().size()-1).getId();
		
		assertNotEquals(repo.findAllByEmailDriver("thebestdriverever@gmail.com").size(),0);
		assertNotEquals(repo.findAllByEmailDriver("thebestdriverever@gmail.com").size(),1);

		long a = (long)repo.findAllByEmailDriver("thebestdriverever@gmail.com").size();
		long b = repo.countByEmailDriver("thebestdriverever@gmail.com");
		assertEquals(a,b);
		
		repo.deleteById(anotherId);
	}

}
