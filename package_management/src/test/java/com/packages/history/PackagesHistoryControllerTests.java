package com.packages.history;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.packages.history.PackagesHistoryController;
import com.packages.history.PackagesHistoryService;
import com.packages.model.PackagesDriverHistory;
import com.packages.model.PackagesSenderHistory;

import net.minidev.json.JSONObject;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
public class PackagesHistoryControllerTests {

	private String uri;
    private MockMvc mvc;
    String cardNumber;
    JSONObject json;
    
	@Mock
	private  PackagesHistoryService service;
	
	@InjectMocks
	private PackagesHistoryController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		
		 json = new JSONObject();
		 json.put("message", "Succes");
		
		MockitoAnnotations.initMocks(this);
		
		mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
	}
	
	@Test
	@DisplayName("getSenderHistory id")
	public void getOnePackages_Sender_Test() throws Exception
	{
		PackagesSenderHistory pack = new PackagesSenderHistory(1,"name", "mailS", "mailD", "senderA",
				"recvA", 45.3f, "12341234",
				"12414", 7821, "stat","sName", "rname", 21, 42,1);
		uri = "/packages/sender/one/" + pack.getId();
		
		when(service.getPackageSender(pack.getId())).thenReturn(pack);
		
		mvc.perform(get(uri))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id",is(pack.getId())))
		.andExpect(jsonPath("$.namePackage",is(pack.getNamePackage())))
		.andExpect(jsonPath("$.emailSender",is(pack.getEmailSender())))
		.andExpect(jsonPath("$.emailDriver",is(pack.getEmailDriver())))
		.andExpect(jsonPath("$.phoneNumberReceiver",is(pack.getPhoneNumberReceiver())))
		.andExpect(jsonPath("$.pin",is(pack.getPin())))
		.andExpect(jsonPath("$.status",is(pack.getStatus())));
		
		verify(service).getPackageSender(pack.getId());
	}
	
	@Test
	@DisplayName("getSenderHistory ")
	public void getPackagesHistorySender_Test() throws Exception
	{
		uri = "/packages/getPackagesSender";
		List<PackagesSenderHistory> list = new ArrayList<PackagesSenderHistory>(); 
		list.add(new PackagesSenderHistory(1,"name", "mailS", "mailD", "senderA",
				"recvA", 45.3f, "12341234",
				"12414", 7821, "stat","sName", "rname", 21, 42,1));
		list.add(new PackagesSenderHistory(2,"Gigel", "mailg", "mailDad", "senderAad",
				"recvA", 80.3f, "12341234",
				"12414", 1234, "statz","bigName", "oneal", 21, 42,1));
		
		when(service.gePackagesHistorySender()).thenReturn(list);
		
		mvc.perform(get(uri))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id",is(list.get(0).getId())))
		.andExpect(jsonPath("$[0].namePackage",is(list.get(0).getNamePackage())))
		.andExpect(jsonPath("$[0].emailSender",is(list.get(0).getEmailSender())))
		.andExpect(jsonPath("$[0].emailDriver",is(list.get(0).getEmailDriver())))
		.andExpect(jsonPath("$[0].phoneNumberReceiver",is(list.get(0).getPhoneNumberReceiver())))
		.andExpect(jsonPath("$[0].pin",is(list.get(0).getPin())))
		.andExpect(jsonPath("$[0].status",is(list.get(0).getStatus())))
		.andExpect(jsonPath("$[1].id",is(list.get(1).getId())))
		.andExpect(jsonPath("$[1].namePackage",is(list.get(1).getNamePackage())))
		.andExpect(jsonPath("$[1].emailSender",is(list.get(1).getEmailSender())))
		.andExpect(jsonPath("$[1].emailDriver",is(list.get(1).getEmailDriver())))
		.andExpect(jsonPath("$[1].phoneNumberReceiver",is(list.get(1).getPhoneNumberReceiver())))
		.andExpect(jsonPath("$[1].pin",is(list.get(1).getPin())))
		.andExpect(jsonPath("$[1].status",is(list.get(1).getStatus())));
		
		
		verify(service).gePackagesHistorySender();
		
	}
	
	@Test
	@DisplayName("getPackagesHistory ")
	public void getPackagesHistoryDriver_Test() throws Exception
	{
		uri="/packages/getPackagesHistoryDriver";
		List<PackagesDriverHistory> list = new ArrayList<PackagesDriverHistory>(); 
		
		list.add(new PackagesDriverHistory(1,"package1","addr1","addr2",4.1f,"12345678","23456789","user1","user2",10,20,30));
		list.add(new PackagesDriverHistory(2,"package2","addr3","addr4",4.3f,"12345678","23456789","user3","user4",10,20,30));
		
		when(service.gePackagesHistoryDriver()).thenReturn(list);
		
		mvc.perform(get(uri))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id",is(list.get(0).getId())))
		.andExpect(jsonPath("$[0].namePackage",is(list.get(0).getNamePackage())))
		.andExpect(jsonPath("$[0].senderAdress",is(list.get(0).getSenderAdress())))
		.andExpect(jsonPath("$[0].phoneNumberSender",is(list.get(0).getPhoneNumberSender())))
		.andExpect(jsonPath("$[0].phoneNumberReceiver",is(list.get(0).getPhoneNumberReceiver())))
		.andExpect(jsonPath("$[0].length",is(list.get(0).getLength())))
		.andExpect(jsonPath("$[0].width",is(list.get(0).getWidth())))
		.andExpect(jsonPath("$[1].id",is(list.get(1).getId())))
		.andExpect(jsonPath("$[1].namePackage",is(list.get(1).getNamePackage())))
		.andExpect(jsonPath("$[1].senderAdress",is(list.get(1).getSenderAdress())))
		.andExpect(jsonPath("$[1].phoneNumberSender",is(list.get(1).getPhoneNumberSender())))
		.andExpect(jsonPath("$[1].phoneNumberReceiver",is(list.get(1).getPhoneNumberReceiver())))
		.andExpect(jsonPath("$[1].length",is(list.get(1).getLength())))
		.andExpect(jsonPath("$[1].width",is(list.get(1).getWidth())));
		verify(service).gePackagesHistoryDriver();
	}
	
	@Test
	@DisplayName("getPackagesHistory ")
	public void getPackageHistory_NotDelivered_Driver_Test() throws Exception
	{
		uri="/packages/getPackagesNotDeliveredDriver";
		List<PackagesDriverHistory> list = new ArrayList<PackagesDriverHistory>(); 
		
		list.add(new PackagesDriverHistory(1,"package1","addr1","addr2",4.1f,"12345678","23456789","user1","user2",10,20,30));
		list.add(new PackagesDriverHistory(2,"package2","addr3","addr4",4.3f,"12345678","23456789","user3","user4",10,20,30));
		
		when(service.getPackagesNotDeliveredDriver()).thenReturn(list);
		
		mvc.perform(get(uri))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id",is(list.get(0).getId())))
		.andExpect(jsonPath("$[0].namePackage",is(list.get(0).getNamePackage())))
		.andExpect(jsonPath("$[0].senderAdress",is(list.get(0).getSenderAdress())))
		.andExpect(jsonPath("$[0].phoneNumberSender",is(list.get(0).getPhoneNumberSender())))
		.andExpect(jsonPath("$[0].phoneNumberReceiver",is(list.get(0).getPhoneNumberReceiver())))
		.andExpect(jsonPath("$[0].length",is(list.get(0).getLength())))
		.andExpect(jsonPath("$[0].width",is(list.get(0).getWidth())))
		.andExpect(jsonPath("$[1].id",is(list.get(1).getId())))
		.andExpect(jsonPath("$[1].namePackage",is(list.get(1).getNamePackage())))
		.andExpect(jsonPath("$[1].senderAdress",is(list.get(1).getSenderAdress())))
		.andExpect(jsonPath("$[1].phoneNumberSender",is(list.get(1).getPhoneNumberSender())))
		.andExpect(jsonPath("$[1].phoneNumberReceiver",is(list.get(1).getPhoneNumberReceiver())))
		.andExpect(jsonPath("$[1].length",is(list.get(1).getLength())))
		.andExpect(jsonPath("$[1].width",is(list.get(1).getWidth())));
		verify(service).getPackagesNotDeliveredDriver();
		
		
	}
	

}
