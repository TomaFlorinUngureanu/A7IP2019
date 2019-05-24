package com.accountManagement.recoverPassword;

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

import com.accountManagement.exceptions.UnknownMatchException;import com.accountManagement.model.Users;
import com.accountManagement.repositories.UsersRepository;
import com.accountManagement.recoverPassword.*;

class ResetPasswordServiceTest {
	private Users userRepository, userRequest;

	@Mock
	UsersRepository usersRepository;
	
	@InjectMocks
	ResetPasswordService resetPasswordService;



	@BeforeEach
	void setUp() throws Exception {

		resetPasswordService = new ResetPasswordService();

		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("login with valid data")
	void testSend_validData() {

		when(usersRepository.existsById(anyString())).thenReturn(true);

	}
	
	@Test
	@DisplayName("login with invalid email")
	void testSend_invalidEmai() {
		
		when(usersRepository.existsById(anyString())).thenReturn(false);
		
		assertThrows(UnknownMatchException.class, () -> resetPasswordService.send("invalid-email@gmail.com","subject","body","newpassword"));
		
	}
	

}
