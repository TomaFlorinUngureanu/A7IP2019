package com.accountManagement.sender.modifyProfileData;

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
import com.accountManagement.model.ProfilesSender;
import com.accountManagement.repositories.ProfilesSenderRepository;

import net.minidev.json.JSONObject;

class ModifySenderProfileDataServiceTest {
	@Mock
	private ProfilesSenderRepository profileRepo; 
	
	@InjectMocks
	private ModifySenderProfileDataService modifySenderProfileDataService;
	
	ChangedProfiles changedProfile = new ChangedProfiles();
	
	
	@BeforeEach
	void setUp() throws Exception {
		JwtUser.setUserName("AurasStegarul");
		changedProfile = new ChangedProfiles();
		changedProfile.setPhone_number("0748731484");
		changedProfile.setName("AurasStegarul");
		changedProfile.setCountry("Tunisia");
		changedProfile.setAddress1("addr1");
		changedProfile.setAddress2("addr2");
		changedProfile.setAddress3("addr3");
		changedProfile.setAddress4("addr4");
		changedProfile.setAddress5("addr5");

		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Change profile")
	void changeProfileDataObjTest() {
		
		when(profileRepo.existsById( anyString() )).thenReturn(true);
		
		Optional<ProfilesSender> senderProfile;
		ProfilesSender profile = new ProfilesSender("AurasStegarul","user","4564543","Romania","a1","a2","a3","a4","a5");
		senderProfile=Optional.of(profile);
		
		when(profileRepo.findById(anyString())).thenReturn(senderProfile);
		when(profileRepo.save(any())).thenReturn(profile);
		
		JSONObject json = new JSONObject();
		json.put("message", "Succes");
		
		assertTrue(json.toString().equals(modifySenderProfileDataService.changeProfileDataObj(changedProfile)));
		
		assertTrue(profile.getAddress1().equals(changedProfile.getAddress1()));
		assertTrue(profile.getAddress2().equals(changedProfile.getAddress2()));
		assertTrue(profile.getAddress3().equals(changedProfile.getAddress3()));
		assertTrue(profile.getAddress4().equals(changedProfile.getAddress4()));
		assertTrue(profile.getAddress5().equals(changedProfile.getAddress5()));
		assertTrue(profile.getName().equals(changedProfile.getName()));
		assertTrue(profile.getCountry().equals(changedProfile.getCountry()));
		assertTrue(profile.getPhone_number().equals(changedProfile.getPhone_number()));
		assertTrue(profile.getEmail().equals(JwtUser.getUserName()));
	}
	
	@Test
	@DisplayName("Change profile")
	void changeProfileDataObjTest_EmailNotFound() {
		
		when(profileRepo.existsById( anyString() )).thenReturn(false);
		
		assertThrows( UnknownMatchException.class , () ->  modifySenderProfileDataService.changeProfileDataObj(changedProfile));
	}
	

}
