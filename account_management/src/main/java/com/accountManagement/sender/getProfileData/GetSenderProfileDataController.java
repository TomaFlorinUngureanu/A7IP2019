package com.accountManagement.sender.getProfileData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountManagement.model.ProfilesSender;




@CrossOrigin("http://localhost:4200")
@RestController
public class GetSenderProfileDataController {
	@Autowired
	GetSenderProfileDataService profilesService;
	
	 @RequestMapping("/accountManagement/getProfileInformation/sender")
		public ProfilesSender getProfile() {
			return profilesService.getProfile();
		}
	

}
