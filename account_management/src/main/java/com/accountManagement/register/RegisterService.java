package com.accountManagement.register;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.model.ProfilesDriver;
import com.accountManagement.model.ProfilesSender;
import com.accountManagement.model.RegisterDetails;
import com.accountManagement.model.Users;
import com.accountManagement.repositories.ProfilesDriverRepository;
import com.accountManagement.repositories.ProfilesSenderRepository;
import com.accountManagement.repositories.UsersRepository;


@Service
public class RegisterService {

	public boolean isPasswordStrong(RegisterDetails user)
	{
        if(user.getPassword().length()<=20 && user.getPassword().length()>=4)
        {
        	 user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())); 
             return true;
        }
        return false;    
	}
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private ProfilesSenderRepository profilesSenderRepository;
	@Autowired
	private ProfilesDriverRepository profilesDriverRepository;
	

	public String addUser(RegisterDetails newUser) 
	{	
	
		if(!isPasswordStrong(newUser)) throw new UnknownMatchException ("Password is not secure. Please enter a password that have between 4 and 20 characters");
		
		if(newUser.getEmail()==null || newUser.getName()==null || newUser.getPhone_number()==null || newUser.getCountry()==null ||
				newUser.getPassword()==null) throw new UnknownMatchException ("You didn't fill all fields (email,name,password,phone_number,country)");
		
		if(usersRepository.existsById(newUser.getEmail())) throw new UnknownMatchException ("This email has been already used for register");
		
		if (profilesSenderRepository.existsById(newUser.getEmail()))
			throw new UnknownMatchException  ("This email has been already used for profile informations for senders");

		if( profilesDriverRepository.existsById(newUser.getEmail()))
			throw new UnknownMatchException  ("This email has been already used for profile informations for drivers");

		
			Users user = new Users(newUser.getEmail(),newUser.getPassword());
			ProfilesSender senderProfile = new ProfilesSender(newUser.getEmail(),newUser.getName(),newUser.getPhone_number()
					,newUser.getCountry() ,null,null,null,null,null);
			ProfilesDriver driverProfile = new ProfilesDriver(newUser.getEmail(),newUser.getName(),newUser.getPhone_number(),newUser.getCountry());
				
			    profilesSenderRepository.save(senderProfile);
				profilesDriverRepository.save(driverProfile);
				usersRepository.save(user);

		JSONObject json = new JSONObject();
		json.put("message", "Success");
		return json.toString();
	}

}
