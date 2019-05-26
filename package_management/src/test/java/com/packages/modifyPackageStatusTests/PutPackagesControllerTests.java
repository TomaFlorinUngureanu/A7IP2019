package com.packages.modifyPackageStatusTests;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packages.ModifyPackageStatus.PutPackagesController;
import com.packages.ModifyPackageStatus.PutPackagesService;
import com.packages.deletePackage.DeletePackageController;
import com.packages.deletePackage.DeletePackageService;
import com.packages.model.ModifyPackageInformation;

import net.minidev.json.JSONObject;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
public class PutPackagesControllerTests {

	   private MockMvc mvc;
	    
	   JSONObject json; 
	    private String uri;
	    ModifyPackageInformation form = new ModifyPackageInformation(5,"status",7012);
	    		
	    
		@Mock
		private  PutPackagesService service;
		
		@InjectMocks
		private PutPackagesController controller;
		
		@BeforeEach
		void setUp() throws Exception {
			
			 json = new JSONObject();
			 json.put("message", "Succes");

			 uri="/packages/modifyStatus";
			MockitoAnnotations.initMocks(this);
			
			mvc = MockMvcBuilders
	                .standaloneSetup(controller)
	                .build();
		}
		
		@Test
		@DisplayName("putPackage controller")
		public void putPackage_test() throws Exception
		{
			when(service.changePackage(form)).thenReturn(json.toString());
			
			ObjectMapper mapper = new ObjectMapper();
			
			mvc.perform(put(uri)
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(mapper.writeValueAsString(form)))
			.andExpect(status().isOk());
		//	.andExpect(jsonPath("$.message",is("Succes")));
			
			verify(service).changePackage(ArgumentMatchers.refEq(form));
			
		}
}
