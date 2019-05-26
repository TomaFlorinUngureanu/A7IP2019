package com.tripManagement.adresses;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

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

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
class LocationControllerTest {

	private MockMvc mvc;
	//private Location loc;
	private String uri = "trip/getLocation/";
	private String loc;
	
	@Mock
	LocationService service;
	
	@InjectMocks
	LocationController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		
		//loc = new Location("Iasi",27.57,47.17);
        loc = "Iasi";
        uri+= loc;
        
		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
	}

	@Test
	@DisplayName("getLocation test")
	void testGetLocation() throws Exception {
		
		MockHttpServletResponse response = mvc.perform(
                get(uri))
                .andExpect(status().isOk())
                .andReturn().getResponse();	}

}
