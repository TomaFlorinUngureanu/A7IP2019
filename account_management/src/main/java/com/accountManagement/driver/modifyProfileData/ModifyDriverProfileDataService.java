package com.accountManagement.driver.modifyProfileData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.model.ChangedProfiles;
import com.accountManagement.model.JwtUser;
import com.accountManagement.model.ProfilesDriver;
import com.accountManagement.repositories.ProfilesDriverRepository;

import net.minidev.json.JSONObject;


@Service
public class ModifyDriverProfileDataService {

	@Autowired
	private ProfilesDriverRepository profileRepo; 

	public List<ProfilesDriver> getProfiles1() {
		return profileRepo.findAll();
	}

	public String changeProfileDataObj(ChangedProfiles changedProfile) {

		if(!profileRepo.existsById(JwtUser.getUserName()) ) throw new UnknownMatchException ("Invalid email address");

		 ProfilesDriver profile = new ProfilesDriver(profileRepo.findById(JwtUser.getUserName()).get());
		 
		 if(changedProfile.getPhone_number()!= null)
		 {
			 profile.setPhone_number(changedProfile.getPhone_number());
		 }
		 
		 if(changedProfile.getCountry()!=null) profile.setCountry(changedProfile.getCountry());
		 
		 if(changedProfile.getName()!=null) profile.setName(changedProfile.getName());

			profileRepo.save(profile);

			JSONObject json = new JSONObject();
			json.put("message", "Success");

			return json.toString();
			}
	
	
}
