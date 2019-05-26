package com.tripManagement.adresses;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
public class LocationServiceTest {
	
	private String loc;
	
    @Autowired
	//@Mock
    LocationService service;
    
	@BeforeEach
	public void setUp() throws Exception {
		
		loc = "Iasi";
		//MockitoAnnotations.initMocks(this);

			}

	@Test
	public void getLocationtest() throws IOException {
		assertNotNull(service.verifyLocation(loc));
	}
		

}
