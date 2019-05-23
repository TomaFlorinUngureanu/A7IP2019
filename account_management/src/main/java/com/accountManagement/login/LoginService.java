package com.accountManagement.login;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.model.Users;
import com.accountManagement.repositories.UsersRepository;

@Service
public class LoginService {

	@Autowired
	private UsersRepository usersRepository;
	
	JwtGenerator jwtGenerator;

	public LoginService(JwtGenerator jwtGenerator) {
		this.jwtGenerator=jwtGenerator;
	}

	public String setUsersObj(Users users) {
		
		if(!usersRepository.existsById(users.getEmail())) throw new UnknownMatchException ("Invalid email address");
		
		if(!BCrypt.checkpw(users.getPassword(), usersRepository.getOne(users.getEmail()).getPassword()))
			   throw new UnknownMatchException("Invalid password");

		JSONObject json = new JSONObject();
		json.put("token", jwtGenerator.generate(users));

		return json.toString();
	}
}
