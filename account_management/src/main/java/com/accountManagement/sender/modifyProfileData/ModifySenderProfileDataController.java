package com.accountManagement.sender.modifyProfileData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.accountManagement.model.ChangedProfiles;
@CrossOrigin("http://localhost:4200")
@RestController
public class ModifySenderProfileDataController {

	@Autowired
	ModifySenderProfileDataService profiles;

	 
	 @RequestMapping(method=RequestMethod.PUT,value="/accountManagement/modifyProfileInformation/sender")
	  public String changeProfileData(@RequestBody ChangedProfiles profile) {
		  return profiles.changeProfileDataObj(profile);
	  }
}
