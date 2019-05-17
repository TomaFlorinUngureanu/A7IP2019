package com.accountManagement.resetPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.model.JwtUser;
import com.accountManagement.model.ResetPassword;
import com.accountManagement.model.Users;
import com.accountManagement.repositories.UsersRepository;

import net.minidev.json.JSONObject;

@Service
public class ResetPasswordServices {
	@Autowired
	public UsersRepository userRepository;
	
	
	public boolean isPasswordStrong(String password)
	{
        if(password.length()<=20 && password.length()>=4)
        {
             return true;
        }
        return false;    
	}
	
	public String setNewPassword(ResetPassword resetPasswordUser)
	{
		if(!userRepository.existsById(JwtUser.getUserName())) throw new UnknownMatchException ("Invalid email address");
        if(!BCrypt.checkpw(resetPasswordUser.getOldPassword() , userRepository.getOne(JwtUser.getUserName()) .getPassword()))
        	throw new UnknownMatchException  ("Invalid old password");
			Users user = new Users();
			user.setEmail(JwtUser.getUserName());
			
			if(!isPasswordStrong(resetPasswordUser.getNewPassword())) throw new UnknownMatchException ("New password is not secure.Please enter a password that have between 4 and 20 characters");
			
			 user.setPassword(BCrypt.hashpw(resetPasswordUser.getNewPassword(), BCrypt.gensalt()));
			userRepository.save(user);
			
			JSONObject json = new JSONObject();
			json.put("message","Success" );
			
			return json.toString();
	}

}
