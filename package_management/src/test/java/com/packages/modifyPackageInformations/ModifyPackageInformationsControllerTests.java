package com.packages.modifyPackageInformations;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packages.deletePackage.DeletePackageController;
import com.packages.deletePackage.DeletePackageService;
import com.packages.model.PackagesSenderHistory;
import com.packages.modifyPackageInformations.ModifyPackageInformationsController;
import com.packages.modifyPackageInformations.ModifyPackageInformationsService;

import net.minidev.json.JSONObject;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
public class ModifyPackageInformationsControllerTests {


	   private MockMvc mvc;
	    
	   
	   JSONObject json; 
	    private  String uri;
	    private final String token = "tokenvalue";
	    
	    PackagesSenderHistory form = new PackagesSenderHistory
	    		(1,"name", "emailSender", "emailDriver", "senderAddress",
			"receiverAddress", 1f, "12345",
			"12345", 6541, "status","senderName", "receiverName", 1, 1,
			1);
	    
		@Mock
		private  ModifyPackageInformationsService service;
		
		@InjectMocks
		private ModifyPackageInformationsController controller;
		
		@BeforeEach
		void setUp() throws Exception {
			
			 json = new JSONObject();
			 json.put("message", "Succes");

			 uri="/packages/modifyPackageInformations";
			MockitoAnnotations.initMocks(this);
			
			mvc = MockMvcBuilders
	                .standaloneSetup(controller)
	                .build();
		}
		
		@Test
		@DisplayName("modifyPackage controller")
		public void changePackage_Test() throws Exception
		{
			when(service.changePackage(form, token)).thenReturn(json.toString());
			
			ObjectMapper mapper = new ObjectMapper();
			mvc.perform(put(uri)
					.contentType(MediaType.APPLICATION_JSON)
					.header("Authorization",token)
					.content(mapper.writeValueAsString(form)))
			.andExpect(status().isOk());
		//	.andExpect(jsonPath("$.message",is("Succes")));
			verify(service).changePackage(ArgumentMatchers.refEq(form),ArgumentMatchers.refEq(token));
		}
}
