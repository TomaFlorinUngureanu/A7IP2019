package com.packages.ModifyStatusSender;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.packages.ModifyStatusDriver.ModifyStatusDriverService;
import com.packages.model.ModifyPackageInformation;

@RestController
public class ModifyStatusSenderController {

	@Autowired
	ModifyStatusSenderService postServ;
	
	 @RequestMapping(method=RequestMethod.PUT,value="/packages/modifyStatusBack")
	 public String postSenderPackage( @RequestBody ModifyPackageInformation form) throws JSONException {
		 return postServ.changePackage(form);
	 }
	
}
