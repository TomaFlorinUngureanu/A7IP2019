package com.packages.repositories;

import static org.hamcrest.CoreMatchers.is;
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

import com.packages.model.PackagesSenderHistory;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CommandsHistoryRepositoryTest {
	
	private PackagesSenderHistory history;
	private int id;
	
	@Autowired
	CommandsHistoryRepository repo;

	@BeforeEach
	void setUp() throws Exception {
		history = new PackagesSenderHistory();
	   
		history.setEmailDriver("gooddriver@gmail.com");
		history.setEmailSender("commonsender@gmail.com");
		history.setHeight(4);
		history.setKilograms(3.00f);
		history.setLength(4);
		history.setWidth(3);
		history.setNamePackage("simplepackage");
		history.setPhoneNumberReceiver("0753974832");
		history.setPhoneNumberSender("075932965999988");
		history.setPin(999988);
		history.setSenderAdress("de aici");
		history.setReceiverAdress("acolo");
		history.setSenderName("Cineva");
		history.setReceiverName("Altcineva");
		history.setStatus("Ready");
		
		repo.save(history);
		
		id = repo.findAll().get(repo.findAll().size()-1).getId();
	}
	
	@AfterEach
	void deleteData() {
		repo.delete(repo.findById(id).get());
	}

	@Test
	@DisplayName("saveData test")
	void testSave() {
		assertThat(repo.existsByEmailSender("commonsender@gmail.com"),is(true));
		assertThat(repo.existsByEmailDriver("gooddriver@gmail.com"),is(true));
		assertThat(repo.existsByPin(999988),is(true));
		repo.delete(repo.findById(id).get());
		assertThat(repo.existsByEmailSender("commonsender@gmail.com"),is(false));
		assertThat(repo.existsByEmailDriver("gooddriver@gmail.com"),is(false));
		assertThat(repo.existsByPin(999988),is(false));
		repo.save(history);
		id = repo.findAll().get(repo.findAll().size()-1).getId();
	}
	
	@Test
	@DisplayName("find by pin test")
	void testFindByPin() {
		assertEquals(repo.findByPin(999988).get().getEmailDriver(),"gooddriver@gmail.com");
		assertEquals(repo.findByPin(999988).get().getEmailSender(),"commonsender@gmail.com");
		assertEquals(repo.findByPin(999988).get().getHeight(),4);
		assertEquals(repo.findByPin(999988).get().getKilograms(),3.00f);
		assertEquals(repo.findByPin(999988).get().getLength(),4);
		assertEquals(repo.findByPin(999988).get().getNamePackage(),"simplepackage");
		assertEquals(repo.findByPin(999988).get().getPhoneNumberReceiver(),"0753974832");
		assertEquals(repo.findByPin(999988).get().getPhoneNumberSender(),"075932965999988");
		assertEquals(repo.findByPin(999988).get().getReceiverAdress(),"acolo");
		assertEquals(repo.findByPin(999988).get().getReceiverName(),"Altcineva");
		assertEquals(repo.findByPin(999988).get().getSenderAdress(),"de aici");
		assertEquals(repo.findByPin(999988).get().getSenderName(),"Cineva");
		assertEquals(repo.findByPin(999988).get().getStatus(),"Ready");
		assertEquals(repo.findByPin(999988).get().getWidth(),3);

	}

	@Test
	@DisplayName("find all by sender/driver email")
	void testFindByEmail() {
		
		PackagesSenderHistory anotherHistory = new PackagesSenderHistory();
	    
		anotherHistory.setEmailDriver("gooddriver@gmail.com");
		anotherHistory.setEmailSender("commonsender@gmail.com");
		anotherHistory.setHeight(4);
		anotherHistory.setKilograms(3.00f);
		anotherHistory.setLength(4);
		anotherHistory.setWidth(3);
		anotherHistory.setNamePackage("apackage");
		anotherHistory.setPhoneNumberReceiver("0753974832");
		anotherHistory.setPhoneNumberSender("075932965999988");
		anotherHistory.setPin(2345);
		anotherHistory.setSenderAdress("de aici");
		anotherHistory.setReceiverAdress("in alta parte");
		anotherHistory.setSenderName("Cineva");
		anotherHistory.setReceiverName("Altcineva");
		anotherHistory.setStatus("Ready");
		
		repo.save(anotherHistory);
		
		System.out.println(repo.findAll().get(repo.findAll().size()-1).getId());
		
		assertNotEquals(repo.findAllByEmailDriver("gooddriver@gmail.com").size(),0);
		assertNotEquals(repo.findAllByEmailDriver("gooddriver@gmail.com").size(),1);

		assertNotEquals(repo.findAllByEmailSender("commonsender@gmail.com").size(),0);
		assertNotEquals(repo.findAllByEmailSender("commonsender@gmail.com").size(),1);

		repo.delete(repo.findByPin(2345).get());
	}
}
