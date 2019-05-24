package com.accountManagement.deleteCard;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.accountManagement.addCards.AddCardController;
import com.accountManagement.addCards.AddCardService;
import com.accountManagement.model.Cards;
import com.accountManagement.model.ProfilesDriver;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
public class DeleteCardControllerTests 
{
private String uri;
	
	
    private MockMvc mvc;
    String cardNumber;
    JSONObject json;
    
	@Mock
	private  DeleteCardService service;
	
	@InjectMocks
	private DeleteCardController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		
		 json = new JSONObject();
		 json.put("message", "Succes");
		cardNumber = "1234 1234 4321 4321";
		uri = "/accountManagement/deleteCard/" + cardNumber;
		
		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
	}
	
	@Test
	@DisplayName("deleteCard controller")
	public void deleteCard_Test_Return_Succes() throws Exception
	{
		when(service.deleteCard(cardNumber)).thenReturn(json.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		mvc.perform(delete(uri))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message",is("Succes")));
	
		verify(service).deleteCard(cardNumber);
	}

}
