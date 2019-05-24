package com.packages.PackageForm;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.packages.model.PackagesSenderHistory;
import com.packages.model.RegisterPackage;

@RestController
@CrossOrigin("http://localhost:4200")
public class PostPackagesController {

	@Autowired
	PostPackagesService postServ;
	
	 @RequestMapping(method=RequestMethod.POST,value="/packages/registerPackage")
	 public String postSenderPackage(@RequestBody RegisterPackage form,@RequestHeader("Authorization") String token) throws IOException, JSONException {
		 return postServ.postPackage(form,token);
	 }

}
