package com.accountManagement.driver.getProfileData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.accountManagement.model.JwtUser;
import com.accountManagement.model.ProfilesDriver;
import com.accountManagement.repositories.ProfilesDriverRepository;

class GetDriverProfileDataServiceTest {

private ProfilesDriver driver;
private Optional <ProfilesDriver> driver2;
	
	@Mock
	ProfilesDriverRepository repo;
	
	@InjectMocks
	GetDriverProfileDataService service;

	
	@BeforeEach
	void setUp() throws Exception {
		driver = new ProfilesDriver();
		JwtUser.setUserName("alexandru@gmail.com");
		driver = new ProfilesDriver();
		driver.setEmail("alexandru@gmail.com");
		driver.setName("Alexandru");
		driver.setCountry("Romania");
		driver.setPhone_number("0757324527");
		driver2 = Optional.of(driver);
				
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Profile Exists Id")
	void getProfileTest() {
		when(repo.existsById(anyString())).thenReturn(false);
		assertThrows(UnknownMatchException.class, () -> service.getProfile());

	}
	
	@Test
	@DisplayName("Profile Return")
	void getProfileTest_Return() {
		when(repo.existsById(anyString())).thenReturn(true);
		when(repo.findById(anyString())).thenReturn(driver2);
		assertEquals(service.getProfile().getEmail(),"alexandru@gmail.com");
	}
}