package com.accountManagement.resetPassword;

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
import com.accountManagement.model.JwtUser;
import com.accountManagement.model.ResetPassword;
import com.accountManagement.model.Users;
import com.accountManagement.repositories.UsersRepository;

import net.minidev.json.JSONObject;


class ResetPasswordServicesTest {

	
		@Mock
		JwtGenerator jwtGenerator;
		@Mock
		private UsersRepository usersRepository;
		@InjectMocks
		private ResetPasswordServices resetPasswordServices;
		private Users user;
		private ResetPassword resetPasswordDetails;
		
		
		@BeforeEach
		void setUp() throws Exception {
			JwtUser.setUserName("bianca");
			user = new Users();
			user.setEmail("bianca");
			user.setPassword(BCrypt.hashpw("parola", BCrypt.gensalt()));
			
			resetPasswordDetails = new ResetPassword();
			resetPasswordDetails.setNewPassword("bianca");
			resetPasswordDetails.setOldPassword("parola");
			
		
			MockitoAnnotations.initMocks(this);
		}

		
		@Test
		@DisplayName("password is strong")
		void testIsPasswordStrong() {
			assertTrue(resetPasswordServices.isPasswordStrong("22222"));
		}
		@Test
		@DisplayName("password is not strong")
		void testIsNotPasswordStrong() {
			assertFalse(resetPasswordServices.isPasswordStrong("123"));
		}
		
		@Test
		@DisplayName("Set new password for resetPasswordServices")
		void testAddUser_invalidLoginDataTablePassword() {
			
			when(usersRepository.existsById(anyString())).thenReturn(false);
			assertThrows(UnknownMatchException.class,() ->resetPasswordServices.setNewPassword(resetPasswordDetails));
		}
			
		@Test
		@DisplayName("Set new password for resetPasswordServices")
		void testAddUser_invalidPassword() {
			when(usersRepository.existsById(anyString())).thenReturn(true);
			when(usersRepository.getOne(anyString())).thenReturn(user);
			JSONObject json = new JSONObject();
			json.put("message","Success" );
			assertEquals(json.toString(),resetPasswordServices.setNewPassword(resetPasswordDetails));
		}
		
		
	
	}