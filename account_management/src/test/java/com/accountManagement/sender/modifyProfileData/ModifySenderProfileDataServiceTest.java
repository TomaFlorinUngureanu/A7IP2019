package com.accountManagement.sender.modifyProfileData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.model.ChangedProfiles;
import com.accountManagement.model.JwtUser;
import com.accountManagement.repositories.ProfilesSenderRepository;

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
		changedProfile.setAddress1("Strada Primaveri");

	}

	@Test
	@DisplayName("Change profile")
	void changeProfileDataObjTest() {
		
		assertThrows(UnknownMatchException.class,()->profileRepo.findById(changedProfile.getName()));
	}

}
