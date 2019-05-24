package com.accountManagement.register;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.login.JwtGenerator;
import com.accountManagement.model.RegisterDetails;
import com.accountManagement.repositories.ProfilesDriverRepository;
import com.accountManagement.repositories.ProfilesSenderRepository;
import com.accountManagement.repositories.UsersRepository;

class RegisterServiceTest {

	@Mock
	private UsersRepository usersRepository;
	@Mock
	private ProfilesSenderRepository profilesSenderRepository;
	@Mock
	private ProfilesDriverRepository profilesDriverRepository;
	@InjectMocks
	private RegisterService registerService;
	
	private RegisterDetails registerDetails,registerRequest;
	
	
	@BeforeEach
	void setUp() throws Exception {

		registerDetails = new RegisterDetails();
		registerDetails.setEmail("user");
		registerDetails.setPassword(BCrypt.hashpw("12345", BCrypt.gensalt()));
		
		registerRequest = new RegisterDetails();
		registerRequest.setEmail("user");
		registerRequest.setPassword("12345");
		registerRequest.setCountry("Romania");
		registerRequest.setPhone_number("0749731484");
		registerRequest.setName("CostelBiju");
		
		registerService = new RegisterService();

		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	@DisplayName("password is strong")
	void testIsPasswordStrong() {
		assertTrue(registerService.isPasswordStrong(registerRequest));
	}
	@Test
	@DisplayName("password is not strong")
	void testIsNotPasswordStrong() {
		RegisterDetails notSecurePassword =registerRequest;
		notSecurePassword.setPassword("123");
		assertFalse(registerService.isPasswordStrong(notSecurePassword));
	}
	@Test
	@DisplayName("Add an user")
	void testAddUser() {
		RegisterDetails notSecurePassword =registerRequest;
		RegisterDetails nullName =registerRequest;
		RegisterDetails nullEmail =registerRequest;
		RegisterDetails nullPhoneNumber =registerRequest;
		RegisterDetails nullCountry =registerRequest;
		notSecurePassword.setPassword("123");
		nullName.setName(null);
		nullEmail.setEmail(null);
		nullPhoneNumber.setPhone_number(null);
		nullCountry.setCountry(null);
		
		when(usersRepository.existsById(anyString())).thenReturn(true);
		when(profilesSenderRepository.existsById(anyString())).thenReturn(true);
		when(profilesDriverRepository.existsById(anyString())).thenReturn(true);
		
		assertAll(
		() ->assertThrows(UnknownMatchException.class, () -> registerService.addUser(notSecurePassword)),
		() ->assertThrows(UnknownMatchException.class, () -> registerService.addUser(nullName)),
		() ->assertThrows(UnknownMatchException.class, () -> registerService.addUser(nullEmail)),
		() ->assertThrows(UnknownMatchException.class, () -> registerService.addUser(nullPhoneNumber)),
		() ->assertThrows(UnknownMatchException.class, () -> registerService.addUser(nullCountry))
		);
	}

}
