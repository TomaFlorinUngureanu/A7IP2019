package com.accountManagement.modifyDriverProfileDataControllerTests;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.accountManagement.driver.modifyProfileData.ModifyDriverProfileDataController;
import com.accountManagement.driver.modifyProfileData.ModifyDriverProfileDataService;
import com.accountManagement.login.LoginController;
import com.accountManagement.login.LoginService;
import com.accountManagement.model.ChangedProfiles;
import com.accountManagement.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
public class ModifyDriverProfileDataControllerTests {
	private final String uri="/accountManagement/modifyProfileInformation/driver";

	
    private MockMvc mvc;
    
    ChangedProfiles profile;
   
	@Mock
	private ModifyDriverProfileDataService service;

	
	@InjectMocks
	private ModifyDriverProfileDataController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		
		profile = new ChangedProfiles("email","name","12345" , "Romania", "adress1",
			"adress2", "adress3", "adress4", "adress5");
		
		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
	}
	
	@Test
	@DisplayName("modifyDriver controller")
	void changeProfileData_Test_Return_Succes() throws Exception 
	{
		ObjectMapper mapper = new ObjectMapper();
		when(service.changeProfileDataObj(profile)).thenReturn("succes");
		
		mvc.perform(put(uri)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(profile)))
		.andExpect(status().isOk());
		
		verify(service).changeProfileDataObj(ArgumentMatchers.refEq(profile));
	}
}
