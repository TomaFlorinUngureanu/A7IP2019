package com.accountManagement.addCardsControllerTests;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.accountManagement.addCards.AddCardController;
import com.accountManagement.addCards.AddCardService;
import com.accountManagement.driver.getProfileData.GetDriverProfileDataController;
import com.accountManagement.driver.getProfileData.GetDriverProfileDataService;
import com.accountManagement.model.Cards;
import com.accountManagement.model.ProfilesDriver;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
public class AddCardsControllerTests {

private final String uri="/accountManagement/addCard";
	
	ProfilesDriver driver;
	
    private MockMvc mvc;
    
    Cards card;
   
    JSONObject json;
	@Mock
	private  AddCardService service;
	
	@InjectMocks
	private AddCardController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		
		 json = new JSONObject();
		 json.put("message", "Succes");
		
		card = new Cards( 1, "sender@gmail.com", "0465 1243 5999 0123", 
				"mm", "yy", "879", "Rwanda","01662");
		
		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
	}
	
	@Test
	@DisplayName("addCard controller")
	void addCard_Test_Return_Succes() throws Exception 
	{
		when(service.addCard(card)).thenReturn("Succes");
		ObjectMapper mapper = new ObjectMapper();
			mvc.perform(post(uri)
			.contentType(MediaType.APPLICATION_JSON)
		     .content((mapper.writeValueAsString(card))))
			.andExpect(status().isOk());
		
			verify(service).addCard(ArgumentMatchers.refEq(card));
	}
	
	
	
}
