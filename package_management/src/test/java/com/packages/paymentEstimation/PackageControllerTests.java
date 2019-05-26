package com.packages.paymentEstimation;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.packages.paymentEstimation.PackageController;
import com.packages.paymentEstimation.PackageService;

import net.minidev.json.JSONObject;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
public class PackageControllerTests {
private String uri;
	
    private MockMvc mvc;
    String cardNumber;
    JSONObject json;
    int userId=1;
	@Mock
	private  PackageService service;
	
	@InjectMocks
	private PackageController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		
		 json = new JSONObject();
		 json.put("price", 1);
		uri = "/packages/estimatePrice/" + userId;
		
		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
	}
	@Test
	@DisplayName("estimate controller")
	public void estimate_Test() throws Exception
	{
		when(service.EstimatePrice(userId, "token")).thenReturn(json.toString());
	
		MockHttpServletResponse response = mvc.perform(
                get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "token"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price",is(1)))
                .andReturn().getResponse();
		
		verify(service).EstimatePrice(userId,"token");
	}
}
