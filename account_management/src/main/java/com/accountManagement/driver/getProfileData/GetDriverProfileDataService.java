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
	

	public ProfilesDriver getProfile() throws UnknownMatchException {
		
		if(!profilesRepository.existsById(JwtUser.getUserName())) throw new UnknownMatchException("Invalid email address");
	
		return profilesRepository.findById(JwtUser.getUserName()).get();
	}
	
	

}
