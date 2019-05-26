package com.packages.deletePackage;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.packages.deletePackage.DeletePackageController;
import com.packages.deletePackage.DeletePackageService;

import net.minidev.json.JSONObject;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
public class DeletePackageControllerTests {

	   private MockMvc mvc;
	    
	   JSONObject json; 
	    private String uri;
	    private final int userId=10;
	    
		@Mock
		private  DeletePackageService service;
		
		@InjectMocks
		private DeletePackageController controller;
		
		@BeforeEach
		void setUp() throws Exception {
			
			 json = new JSONObject();
			 json.put("message", "Succes");

			 uri="/packages/deletePackage/" + userId;
			MockitoAnnotations.initMocks(this);
			
			mvc = MockMvcBuilders
	                .standaloneSetup(controller)
	                .build();
		}
		
		@Test
		@DisplayName("deletePackage controller")
		public void postSenderPackage_Test() throws Exception
		{
			when(service.deletePackage(userId)).thenReturn(json.toString());
			
			ObjectMapper mapper = new ObjectMapper();
			mvc.perform(delete(uri))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message",is("Succes")));
			
			verify(service).deletePackage((userId));
		}
}
