package com.accountManagement.driver.modifyProfileData;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.model.ChangedProfiles;
import com.accountManagement.model.JwtUser;
import com.accountManagement.model.ProfilesDriver;
import com.accountManagement.repositories.ProfilesDriverRepository;

import net.minidev.json.JSONObject;

class ModifyDriverProfileDataServiceTest {
	
	@Mock
	private ProfilesDriverRepository repo; 
	
	@InjectMocks
	private ModifyDriverProfileDataService service;
	
	ChangedProfiles changedProfile;

	@BeforeEach
	void setUp() throws Exception {
		JwtUser.setUserName("AurasStegarul");
		changedProfile = new ChangedProfiles();
		changedProfile = new ChangedProfiles();
		changedProfile.setPhone_number("0753982843");
		changedProfile.setName("AurasStegarul");
		changedProfile.setCountry("Romania");
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("change driver's profile data")
	void testModifyData() {
		when(repo.existsById( anyString() )).thenReturn(true);
		
		ProfilesDriver driver = new ProfilesDriver("AurasStegarul","Vasile","0752845932","Polonia");
		Optional<ProfilesDriver> optionalDriver = Optional.of(driver);

		when(repo.findById(anyString())).thenReturn(optionalDriver);
		when(repo.save(any())).thenReturn(driver);
		
		JSONObject json = new JSONObject();
		json.put("message", "Success");

		assertTrue(json.toString().equals(service.changeProfileDataObj(changedProfile)));
		
		assertTrue(driver.getEmail().equals(JwtUser.getUserName()));
		assertTrue(driver.getName().equals(changedProfile.getName()));
		assertTrue(driver.getCountry().equals(changedProfile.getCountry()));
		assertTrue(driver.getPhone_number().equals(changedProfile.getPhone_number()));
	}

	@Test
	@DisplayName("EmailNotFound test")
	void testEmailNotFound() {
		
		when(repo.existsById( anyString() )).thenReturn(false);
		
		assertThrows( UnknownMatchException.class , () ->  service.changeProfileDataObj(changedProfile));
	}
}
