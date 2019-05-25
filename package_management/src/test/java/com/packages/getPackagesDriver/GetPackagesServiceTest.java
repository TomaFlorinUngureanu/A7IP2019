package com.packages.getPackagesDriver;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.packages.model.JwtUser;
import com.packages.model.JwtUserDetails;
import com.packages.model.PackagesDriverHistory;
import com.packages.repositories.CommandsHistoryRepository;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
class GetPackagesServiceTest {
	
	List<PackagesDriverHistory> list;
	
	@Mock
	private CommandsHistoryRepository repo;
	
	@InjectMocks
	private GetPackagesService service;

	@BeforeEach
	void setUp() throws Exception {
		
		list=new ArrayList<PackagesDriverHistory>();
		
		JwtUser.setUserName("Gabriel");
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testSuccess() throws IOException {
	    
		//list= service.getPackages("Iasi", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6IkdhYnJpZWwifQ.p_7SM8m8eElfTF98Crun5qdBZ7Vim1gRUrB3Kp5FLPQCvNR5XYlwmj_k8_xwL9q-cUG342jDjy3gj5MI4Uwhsw") ;
		
		
		//assertNotNull(service.getPackages("Satu Mare", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6IkdhYnJpZWwifQ.p_7SM8m8eElfTF98Crun5qdBZ7Vim1gRUrB3Kp5FLPQCvNR5XYlwmj_k8_xwL9q-cUG342jDjy3gj5MI4Uwhsw"));
		
		//System.out.println(service.getPackages("Satu Mare", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6IkdhYnJpZWwifQ.p_7SM8m8eElfTF98Crun5qdBZ7Vim1gRUrB3Kp5FLPQCvNR5XYlwmj_k8_xwL9q-cUG342jDjy3gj5MI4Uwhsw"));
		
		//assertEquals(list.size(),5);

	}

}
