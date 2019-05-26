package com.packages.PackageForm;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.packages.PackageForm.PostPackagesController;
import com.packages.PackageForm.PostPackagesService;
import com.packages.model.RegisterPackage;

import net.minidev.json.JSONObject;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
public class PostPackagesControllerTests 
{
	
    private MockMvc mvc;
    String cardNumber;
    JSONObject json;
    private String uri;
    private final String token="";
    
	RegisterPackage pack1 = new RegisterPackage("name",
			"emailSender", "senderA", "receivera", 
			57.4f, 21,
			32, 21, "12345", 
			"54312", "rec","seen");
    
	@Mock
	private  PostPackagesService service;
	
	@InjectMocks
	private PostPackagesController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		
		 json = new JSONObject();
		 json.put("message", "Success");
		uri = "/packages/registerPackage";
				
		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
	}
	
	@Test
	@DisplayName("deleteCard controller")
	public void deleteCard_Test_Return_Succes() throws Exception
	{
		when(service.postPackage(pack1, "token")).thenReturn(json.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		MockHttpServletResponse response = mvc.perform(
                post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(pack1))
                .header("Authorization", "token"))
//				.andExpect(jsonPath("$.message",is("Success")))
                .andReturn().getResponse();
	
		
		assertEquals(response.getStatus(),HttpStatus.OK.value());
		
		verify(service).postPackage(ArgumentMatchers.refEq(pack1), ArgumentMatchers.refEq("token"));

	}
}
