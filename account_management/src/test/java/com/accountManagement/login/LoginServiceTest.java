package com.accountManagement.login;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCrypt;


import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.model.Users;
import com.accountManagement.repositories.UsersRepository;

import net.minidev.json.JSONObject;

class LoginServiceTest {

	private Users userRepository, userRequest;

	@Mock
	JwtGenerator jwtGenerator;

	@Mock
	UsersRepository usersRepository;

	@InjectMocks
	LoginService loginService;

	@BeforeEach
	void setUp() throws Exception {

		userRepository = new Users();
		userRepository.setEmail("user");
		userRepository.setPassword(BCrypt.hashpw("12345", BCrypt.gensalt()));
		
		userRequest = new Users();
		userRequest.setEmail("user");
		userRequest.setPassword("12345");

		loginService = new LoginService(jwtGenerator);

		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("login with valid data")
	void testSetUsersObj_validData() {

		when(usersRepository.existsById(anyString())).thenReturn(true);
		when(usersRepository.getOne(anyString())).thenReturn(userRepository);
		when(jwtGenerator.generate(userRequest)).thenReturn("token");
		
		JSONObject json = new JSONObject();
		json.put("token", "token");

		String result = loginService.setUsersObj(userRequest);

		assertTrue(json.toString().equals(result));
	}
	
	@Test
	@DisplayName("login with invalid email")
	void testSetUserObj_invalidEmai() {
		
		when(usersRepository.existsById(anyString())).thenReturn(false);
		
		assertThrows(UnknownMatchException.class, () -> loginService.setUsersObj(userRequest));
		
	}
	
	@Test
	@DisplayName("login with invalid password")
	void testSetUserObj_invalidPassword() {
		
		when(usersRepository.existsById(anyString())).thenReturn(true);
		when(usersRepository.getOne(anyString())).thenReturn(userRepository);
		
		userRequest.setPassword("123456");
		
		assertThrows(UnknownMatchException.class, () -> loginService.setUsersObj(userRequest));
		
		}
	
}
