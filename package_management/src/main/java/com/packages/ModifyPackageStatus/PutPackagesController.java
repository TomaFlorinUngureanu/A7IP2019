package com.packages.ModifyPackageStatus;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.packages.model.ModifyPackageInformation;

@RestController
@CrossOrigin("http://localhost:4200")
public class PutPackagesController {

	@Autowired
	PutPackagesService postServ;
	
	 @RequestMapping(method=RequestMethod.PUT,value="/packages/modifyStatus")
	 public String postSenderPackage( @RequestBody ModifyPackageInformation form) throws JSONException {
		 return postServ.changePackage(form);
	 }
}
