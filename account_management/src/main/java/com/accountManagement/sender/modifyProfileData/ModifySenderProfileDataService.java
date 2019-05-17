package com.accountManagement.sender.modifyProfileData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.model.ChangedProfiles;
import com.accountManagement.model.JwtUser;
import com.accountManagement.model.ProfilesSender;
import com.accountManagement.repositories.ProfilesSenderRepository;

import net.minidev.json.JSONObject;


@Service
public class ModifySenderProfileDataService {

	@Autowired
	private ProfilesSenderRepository profileRepo; 


	public String changeProfileDataObj(ChangedProfiles changedProfile) {

		if(!profileRepo.existsById(JwtUser.getUserName()) ) throw new UnknownMatchException ("Invalid email address");
		
		 ProfilesSender profile = new ProfilesSender();
		profile=profileRepo.findById(JwtUser.getUserName()).get();
		
		 if(changedProfile.getAddress1() != null)
		 {
			 profile.setAddress1(changedProfile.getAddress1());
		 }
		 if(changedProfile.getAddress2()!= null)
		 {
			 profile.setAddress2(changedProfile.getAddress2());
		 }
		 if(changedProfile.getAddress3()!= null)
		 {
			 profile.setAddress3(changedProfile.getAddress3());
		 }
		 if(changedProfile.getAddress4()!= null)
		 {
			 profile.setAddress4(changedProfile.getAddress4());
		 }
		 if(changedProfile.getAddress5()!= null)
		 {
			 profile.setAddress5(changedProfile.getAddress5());
		 }
		 
		 if(changedProfile.getPhone_number()!= null)
		 {
			 profile.setPhone_number(changedProfile.getPhone_number());
		 }
		 
		 if(changedProfile.getName()!=null) profile.setName(changedProfile.getName());
		 
		 if(changedProfile.getCountry()!=null) profile.setCountry(changedProfile.getCountry());
		
			profileRepo.save(profile);
			
			JSONObject json = new JSONObject();
			json.put("message", "Succes");

			return json.toString();
	}
	
	
}
