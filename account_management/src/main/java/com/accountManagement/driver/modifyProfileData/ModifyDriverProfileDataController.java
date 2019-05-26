package com.accountManagement.driver.modifyProfileData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.accountManagement.model.ChangedProfiles;

@RestController
public class ModifyDriverProfileDataController {

	@Autowired
	ModifyDriverProfileDataService profiles;
	 
	 @RequestMapping(method=RequestMethod.PUT,value="/accountManagement/modifyProfileInformation/driver")
	  public String changeProfileData(@RequestBody ChangedProfiles profile) {
		  return profiles.changeProfileDataObj(profile);
	  }
}
