package com.accountManagement.login;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import com.accountManagement.model.Users;
import com.accountManagement.security.config.JwtSecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import net.minidev.json.JSONObject;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
class LoginControllerTest {

	private final String uri="/login";
	
	Users user;
	
    private MockMvc mvc;
   
	@Mock
	private LoginService loginService;
	
	@InjectMocks
	private LoginController loginController;

	@BeforeEach
	void setUp() throws Exception {
		
		user=new Users("Gabriel","12345");
		
		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(loginController)
                .build();
	}

	@Test
	@DisplayName("login controller")
	void testSetUsersObj() throws Exception {
			
			when(loginService.setUsersObj(user)).thenReturn("Success");
			
			ObjectMapper mapper = new ObjectMapper();
			
			 MockHttpServletResponse response = mvc.perform(
		                post(uri)
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(mapper.writeValueAsString(user)))
		                .andReturn().getResponse();
			 
			 assertEquals(response.getStatus(),HttpStatus.OK.value());
		    // assertTrue(response.getContentAsString().equals("Success"));

	
	/*
		ObjectMapper mapper = new ObjectMapper();
		
		mvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user)))
		.andExpect(status().isOk());
		//.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		//verify(service).setUsersObj((org.mockito.Matchers.refEq(user)));
		 */
	}

}
