package com.accountManagement.sender.getProfileData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.model.JwtUser;
import com.accountManagement.model.ProfilesSender;
import com.accountManagement.repositories.ProfilesSenderRepository;




@Service
public class GetSenderProfileDataService {

	@Autowired
	private ProfilesSenderRepository profilesRepository;

	public ProfilesSender getProfile() {
		
        if(!profilesRepository.existsById(JwtUser.getUserName())) throw new UnknownMatchException("Invalid email address");
		
		return profilesRepository.findById(JwtUser.getUserName()).get();
	}

}
