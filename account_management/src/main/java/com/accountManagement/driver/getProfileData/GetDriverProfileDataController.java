package com.accountManagement.driver.getProfileData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountManagement.model.ProfilesDriver;

@RestController
public class GetDriverProfileDataController {
	@Autowired
	GetDriverProfileDataService profilesService;

	
	 @RequestMapping("/accountManagement/getProfileInformation/driver/{email}")
		public ProfilesDriver getProfile(@PathVariable String email)  {
		 return profilesService.getProfile(email);
		}
	 
	
	 
}
