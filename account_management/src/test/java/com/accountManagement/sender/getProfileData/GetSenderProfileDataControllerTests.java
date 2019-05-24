package com.accountManagement.sender.getProfileData;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.accountManagement.driver.getProfileData.GetDriverProfileDataController;
import com.accountManagement.driver.getProfileData.GetDriverProfileDataService;
import com.accountManagement.model.ProfilesDriver;
import com.accountManagement.model.ProfilesSender;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
public class GetSenderProfileDataControllerTests {
	private final String uri="/accountManagement/getProfileInformation/sender";
	
	ProfilesSender sender;
	
    private MockMvc mvc;
   
	@Mock
	private  GetSenderProfileDataService service;
	
	@InjectMocks
	private GetSenderProfileDataController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		
		sender= new ProfilesSender("email@gmail.com","gigel","123456",
				"Romania","a2","a3","a4", "a5", "a1");
		
		
		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
	}
	
	@Test
	@DisplayName("getSender controller")
	void getProfile_Test_Return_Succes() throws Exception {
		
		when(service.getProfile()).thenReturn(sender);
		ObjectMapper mapper = new ObjectMapper();
		 MockHttpServletResponse response = mvc.perform(
	                get(uri))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	                .andExpect(jsonPath("$.email",is(sender.getEmail())))
	                .andExpect(jsonPath("$.name",is(sender.getName())))
	                .andExpect(jsonPath("$.phone_number",is(sender.getPhone_number())))
	                .andExpect(jsonPath("$.country",is(sender.getCountry())))
	                .andExpect(jsonPath("$.address1",is(sender.getAddress1())))
	                .andReturn().getResponse();
		 
		 verify(service).getProfile();		
	}	
}
