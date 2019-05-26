package com.packages.modifyPackageInformations;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.packages.model.PackagesSenderHistory;

@RestController

public class ModifyPackageInformationsController {
	@Autowired
	ModifyPackageInformationsService putServ;
	
	 @RequestMapping(method=RequestMethod.PUT,value="/packages/modifyPackageInformations")
	 public String postSenderPackage( @RequestBody PackagesSenderHistory form,@RequestHeader("Authorization") String token) throws JSONException, IOException {
		 return putServ.changePackage(form,token);
}
}
