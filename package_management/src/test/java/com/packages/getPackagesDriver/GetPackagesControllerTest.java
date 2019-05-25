package com.packages.getPackagesDriver;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import com.packages.model.PackagesDriverHistory;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
class GetPackagesControllerTest {

	private final String uri="/packages/getPackagesNear/Iasi";
	private final String token="";
	List<PackagesDriverHistory> list;
    private MockMvc mvc;
    
    @Mock
    GetPackagesService service;
    
    @InjectMocks
    GetPackagesController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		
		list=new ArrayList<PackagesDriverHistory>();
		
		list.add(new PackagesDriverHistory(1,"package1","addr1","addr2",4,"12345678","23456789","user1","user2",10,20,30));
		list.add(new PackagesDriverHistory(2,"package2","addr3","addr4",4,"12345678","23456789","user3","user4",10,20,30));
		
		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
	}

	@Test
	void test() throws Exception {
		when(service.getPackages("Iasi","token")).thenReturn(list);
		
		ObjectMapper mapper = new ObjectMapper();
		
		MockHttpServletResponse response = mvc.perform(
                get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "token"))
                .andReturn().getResponse();
		
		Gson gson=new Gson();
		list=gson.fromJson(response.getContentAsString(), new TypeToken<List<PackagesDriverHistory>>(){}.getType());
	 
	 assertEquals(response.getStatus(),HttpStatus.OK.value());
	 
     assertEquals(list.size(),2);
     
     assertAll(
    		 () -> assertEquals(list.get(0).getKilograms(),4),
    		 () -> assertEquals(list.get(0).getHeight(),30),
             () -> assertEquals(list.get(0).getLength(),10),
             () -> assertEquals(list.get(0).getWidth(),20),
             () -> assertEquals(list.get(0).getSenderAdress(),"addr1"),
             () -> assertEquals(list.get(0).getNamePackage(),"package1"),
             () -> assertEquals(list.get(0).getReceiverAdress(),"addr2"),
             () -> assertEquals(list.get(0).getSenderName(),"user1"),
             () -> assertEquals(list.get(0).getReceiverName(),"user2"),
             () -> assertEquals(list.get(0).getPhoneNumberSender(),"12345678"),
             () -> assertEquals(list.get(0).getPhoneNumberReceiver(),"23456789"),
    		 () -> assertEquals(list.get(1).getKilograms(),4),
    		 () -> assertEquals(list.get(1).getHeight(),30),
             () -> assertEquals(list.get(1).getLength(),10),
             () -> assertEquals(list.get(1).getWidth(),20),
             () -> assertEquals(list.get(1).getSenderAdress(),"addr3"),
             () -> assertEquals(list.get(1).getNamePackage(),"package2"),
             () -> assertEquals(list.get(1).getReceiverAdress(),"addr4"),
             () -> assertEquals(list.get(1).getSenderName(),"user3"),
             () -> assertEquals(list.get(1).getReceiverName(),"user4"),
             () -> assertEquals(list.get(1).getPhoneNumberSender(),"12345678"),
             () -> assertEquals(list.get(1).getPhoneNumberReceiver(),"23456789")
    		 );
	}

}
