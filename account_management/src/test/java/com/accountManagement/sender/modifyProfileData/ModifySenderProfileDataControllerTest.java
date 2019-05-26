package com.accountManagement.sender.modifyProfileData;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

import com.accountManagement.model.ChangedProfiles;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
class ModifySenderProfileDataControllerTest {

private final String uri = "/accountManagement/modifyProfileInformation/sender";
	
    private MockMvc mvc;
    
    ChangedProfiles profile;
    
    JSONObject json;

	@Mock
	private  ModifySenderProfileDataService service;
	
	@InjectMocks
	private ModifySenderProfileDataController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		profile = new ChangedProfiles();
		profile.setName("Gigel");
		profile.setPhone_number("0753683821");
		profile.setCountry("Polonia");;
		 json = new JSONObject();
		json.put("message", "Success");

		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
	}

	@Test
	@DisplayName("modifySender controller") 
	public void changeProfileData_Test_Return_Succes() throws Exception
	{
		when(service.changeProfileDataObj(profile)).thenReturn(json.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		mvc.perform(put(uri)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(profile)))
		.andExpect(status().isOk());
				
		verify(service).changeProfileDataObj(ArgumentMatchers.refEq(profile));
	}

}
