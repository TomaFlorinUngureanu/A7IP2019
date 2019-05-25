package com.accountManagement.driver.modifyProfileData;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

import com.accountManagement.deleteCard.DeleteCardController;
import com.accountManagement.deleteCard.DeleteCardService;
import com.accountManagement.model.ChangedProfiles;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
public class ModifyDriverProfileDataControllerTests 
{
	private final String uri = "/accountManagement/modifyProfileInformation/driver";
	
    private MockMvc mvc;
    
    ChangedProfiles profile;
    
    JSONObject json;

	@Mock
	private  ModifyDriverProfileDataService service;
	
	@InjectMocks
	private ModifyDriverProfileDataController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		profile = new ChangedProfiles("gigel@gmail.com","gigel", 
				"123556", 
				"Rwanda", "adresa1",
		"strada", "a doua strada", 
		"adresa 4", "testAdresa");
		
		 json = new JSONObject();
		json.put("message", "Success");

		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
	}
	
	@Test
	@DisplayName("modifyDriver controller") 
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
