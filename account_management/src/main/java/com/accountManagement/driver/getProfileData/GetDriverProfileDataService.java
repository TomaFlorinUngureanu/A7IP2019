package com.accountManagement.driver.getProfileData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.model.JwtUser;
import com.accountManagement.model.ProfilesDriver;
import com.accountManagement.repositories.ProfilesDriverRepository;


@Service
public class GetDriverProfileDataService {

	@Autowired
	private ProfilesDriverRepository profilesRepository;
	

	public ProfilesDriver getProfile(String email) throws UnknownMatchException {
		
		if(!profilesRepository.existsById(email)) throw new UnknownMatchException("Invalid driver email address");
	
		return profilesRepository.findById(JwtUser.getUserName()).get();
	}
	
	

}
