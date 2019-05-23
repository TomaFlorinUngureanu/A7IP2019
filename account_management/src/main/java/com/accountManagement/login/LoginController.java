package com.accountManagement.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.accountManagement.model.Users;


@CrossOrigin("http://localhost:4200")
@RestController
public class LoginController {

	@Autowired
	LoginService usersService;
	
	 @RequestMapping(method=RequestMethod.POST,value="/login")
	 public String setUsersObj(@RequestBody Users users) {
		 return (usersService.setUsersObj(users));
	 }	 
	
}
