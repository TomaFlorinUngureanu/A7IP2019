package com.packages.deletePackage;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("http://localhost:4200")
public class DeletePackageController {

	@Autowired
	DeletePackageService deleteServ;
	
	 @RequestMapping(method=RequestMethod.DELETE,value="/packages/deletePackage/{id}")
	 public String postSenderPackage(@PathVariable int id) throws IOException, JSONException {
		 return deleteServ.deletePackage(id);
	 }

	
}
