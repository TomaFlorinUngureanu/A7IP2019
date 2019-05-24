package com.accountManagement.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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

import com.accountManagement.model.Cards;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CardsRepositoryTest {

	private Cards card;
	
	@Autowired
	CardsRepository repo;
	
	@BeforeEach
	void setUp() throws Exception {
		card = new Cards();
		card.setEmailSender("blablabla@gmail.com");
		card.setCardNumber("4678239500013465");
		card.setCountry("Romania");
		card.setCvv("354");
		card.setMm("12");
		card.setYy("99");
		card.setZipCode("709453");
		repo.save(card);
	}
	
	@AfterEach
	void deleteData() throws Exception{
		repo.delete(repo.findByCardNumber("4678239500013465"));
	}

	@Test
	@DisplayName("save in CardsRepository")
	void testSave() {
		
		 assertThat(repo.existsByEmailSender("blablabla@gmail.com"),is(true));
	     assertThat(repo.existsByCardNumber("4678239500013465"),is(true));
	}
	
	@Test
	@DisplayName("find by card name in CardsRepository")
	void testFindByCardNumber() {
		 assertNotNull(repo.findByCardNumber("4678239500013465"));
		 assertEquals(repo.findByCardNumber("4678239500013465").getCardNumber(),"4678239500013465");
		 assertEquals(repo.findByCardNumber("4678239500013465").getEmailSender(),"blablabla@gmail.com");
		 assertEquals(repo.findByCardNumber("4678239500013465").getCountry(),"Romania");
		 assertEquals(repo.findByCardNumber("4678239500013465").getCvv(),"354");
		 assertEquals(repo.findByCardNumber("4678239500013465").getMm(),"12");
		 assertEquals(repo.findByCardNumber("4678239500013465").getYy(),"99");
		 assertEquals(repo.findByCardNumber("4678239500013465").getZipCode(),"709453");

	}
	
	@Test
	@DisplayName("find all by emailSender in CardsRepository")
	void testFindAllByEmailSEnder() {
		 Cards altCard = new Cards();
		 altCard.setEmailSender("blablabla@gmail.com");
	     altCard.setCardNumber("9503929400015326");
		 altCard.setCountry("Romania");
		 altCard.setCvv("364");
		 altCard.setMm("12");
		 altCard.setYy("99");
		 altCard.setZipCode("709453");
		 repo.save(altCard);
		 assertThat(repo.existsByEmailSender("blablabla@gmail.com"),is(true));
	     assertThat(repo.existsByCardNumber("9503929400015326"),is(true));
	     
	     assertNotEquals(repo.findAllByEmailSender("blablabla@gmail.com").size(),0);
	     assertNotEquals(repo.findAllByEmailSender("blablabla@gmail.com").size(),1);

	     
	   	 repo.delete(repo.findByCardNumber("9503929400015326"));
	}
	
}
