package com.accountManagement.register;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.accountManagement.login.LoginController;
import com.accountManagement.model.RegisterDetails;
import com.accountManagement.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

class RegisterControllerTest {

	private final String uri="/login";
	RegisterDetails user;
	private MockMvc mvc;
	@Mock
	private RegisterService userRegisterDataService;
	@InjectMocks
	private RegisterController registerController;
	@BeforeEach
	void setUp() throws Exception {
		user=new RegisterDetails();
		user.setName("Gabriel");
		user.setPassword("12345");
		
		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(registerController)
                .build();
	}

	@Test
	@DisplayName("Register controller")
	void registerUserTest()throws Exception {

		
		when(registerController.registerUser(user)).thenReturn("Succes");
		
		ObjectMapper mapper = new ObjectMapper();
		
		 MockHttpServletResponse response = mvc.perform(
	                post(uri)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(mapper.writeValueAsString(user)))
	                .andReturn().getResponse();
		 
		 System.out.println(response.getContentAsString()+ "|||");
		 
		 assertEquals(response.getStatus(),HttpStatus.NOT_FOUND.value());
	}

}
