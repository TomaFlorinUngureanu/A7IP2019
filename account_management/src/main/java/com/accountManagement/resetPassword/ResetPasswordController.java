package com.accountManagement.resetPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.accountManagement.model.ResetPassword;

@CrossOrigin("http://localhost:4200")
@RestController
public class ResetPasswordController {

	@Autowired
	ResetPasswordServices resetPasswordService;
	
	 @RequestMapping(method=RequestMethod.PUT,value="accountManagement/resetPassword")
	 public ResponseEntity<String> setNewPassword(@RequestBody ResetPassword users) {
	 return ResponseEntity.ok(resetPasswordService.setNewPassword(users));
	 
	 }
}
