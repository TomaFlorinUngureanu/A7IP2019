package com.accountManagement.driver.getProfileData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountManagement.model.ProfilesDriver;

@CrossOrigin("http://localhost:4200")
@RestController
public class GetDriverProfileDataController {
	@Autowired
	GetDriverProfileDataService profilesService;

	
	 @RequestMapping("/accountManagement/getProfileInformation/driver")
		public ResponseEntity<ProfilesDriver> getProfile()  {
		 return ResponseEntity.ok(profilesService.getProfile());
		}
	 
	
	 
}
