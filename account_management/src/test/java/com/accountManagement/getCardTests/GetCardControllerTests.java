package com.accountManagement.getCardTests;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import com.accountManagement.getCard.GetCardController;
import com.accountManagement.getCard.GetCardService;
import com.accountManagement.model.Cards;
import com.accountManagement.model.ChangedProfiles;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
public class GetCardControllerTests {
	
	private final String uri="/accountManagement/getCards";
    private MockMvc mvc;
    
    ChangedProfiles profile;
   
	@Mock
	private GetCardService service;

	
	@InjectMocks
	private GetCardController controller;
	List<Cards> cards = new ArrayList<Cards>();
	
	@BeforeEach
	void setUp() throws Exception {
		Cards card = new Cards( 1, "sender@gmail.com", "0465 1243 5999 0123", 
				"mm", "yy", "879", "Rwanda","01662");
		Cards card2 = new Cards( 2, "anotherSender@gmail.com", "0423 1243 5999 0123", 
				"mm", "yy", "879", "Russia","01232");
		cards.add(card);
		cards.add(card2);
		
		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
	}
	
	@Test
	@DisplayName("getCard controller")
	void getCards_Test_Return_Succes() throws Exception 
	{
		ObjectMapper mapper = new ObjectMapper();
		
		when(service.getCards()).thenReturn(cards);
		mvc.perform(get(uri))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$",Matchers.hasSize(2)))
		.andExpect(jsonPath("$[0].id",is(cards.get(0).getId())))
		.andExpect(jsonPath("$[0].emailSender",is(cards.get(0).getEmailSender())))
		.andExpect(jsonPath("$[1].id",is(cards.get(1).getId())))
		.andExpect(jsonPath("$[1].emailSender",is(cards.get(1).getEmailSender())));
		
		verify(service).getCards();
	}

}
