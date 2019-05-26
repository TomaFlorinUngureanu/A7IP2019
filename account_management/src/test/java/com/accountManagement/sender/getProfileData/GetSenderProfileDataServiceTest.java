package com.accountManagement.sender.getProfileData;

import static org.junit.jupiter.api.Assertions.assertAll;
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
import com.accountManagement.model.ProfilesSender;
import com.accountManagement.repositories.ProfilesSenderRepository;

class GetSenderProfileDataServiceTest {
	
	@InjectMocks
	GetSenderProfileDataService getSenderProfileService;
	@Mock
	private ProfilesSenderRepository profilesRepository;
	
	private ProfilesSender sender;
	private Optional <ProfilesSender> sender2;
	@BeforeEach
	void setUp() throws Exception {
		JwtUser.setUserName("User");
		sender = new ProfilesSender();
		sender.setEmail("alexandru@gmail.com");
		sender.setName("Alexandru");
		sender.setCountry("Romania");
		sender.setPhone_number("0757324527");
		sender2 = Optional.of(sender);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Test Get Profile")
	void testGetProfile() {
		when(profilesRepository.existsById(anyString())).thenReturn(false);
		assertThrows(UnknownMatchException.class,() -> getSenderProfileService.getProfile());
	}
	
	@Test
	@DisplayName("Test Get Profile")
	void testGetProfileFindUser() {
		when(profilesRepository.existsById(anyString())).thenReturn(true);
		when(profilesRepository.findById(anyString())).thenReturn(sender2);
		assertAll(
		()->assertEquals("alexandru@gmail.com", getSenderProfileService.getProfile().getEmail()),
		()->assertEquals("Alexandru", getSenderProfileService.getProfile().getName()),
		()->assertEquals("Romania", getSenderProfileService.getProfile().getCountry()),
		()->assertEquals("0757324527", getSenderProfileService.getProfile().getPhone_number())
		);
	}
}
