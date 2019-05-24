package com.accountManagement.driver.getProfileData;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.Column;
import javax.persistence.Id;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.login.LoginController;
import com.accountManagement.login.LoginService;
import com.accountManagement.model.ProfilesDriver;
import com.accountManagement.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
public class GetDriverProfileDataControllerTests 
{
	private final String uri="/accountManagement/getProfileInformation/driver";
	
	ProfilesDriver driver;
	
    private MockMvc mvc;
   
	@Mock
	private  GetDriverProfileDataService service;
	
	@InjectMocks
	private GetDriverProfileDataController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		
		driver = new ProfilesDriver("email@gmail.com","123456",
				"Romania","Gigel");
		
		
		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
	}
	
	@Test
	@DisplayName("getDriver controller")
	void getProfile_Test_Return_Succes() throws Exception {
		when(service.getProfile()).thenReturn(driver);
		ObjectMapper mapper = new ObjectMapper();
		 MockHttpServletResponse response = mvc.perform(
	                get(uri))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	                .andExpect(jsonPath("$.email",is(driver.getEmail())))
	                .andExpect(jsonPath("$.name",is(driver.getName())))
	                .andExpect(jsonPath("$.phone_number",is(driver.getPhone_number())))
	                .andExpect(jsonPath("$.country",is(driver.getCountry())))
	                .andReturn().getResponse();
		 
		 verify(service).getProfile();		
	}	
	
	
	
}
