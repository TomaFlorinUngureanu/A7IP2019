package com.packages.getPackagesDriver;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.packages.model.PackagesDriverHistory;
import com.packages.model.PackagesSenderHistory;


@RestController
@CrossOrigin("http://localhost:4200")
public class GetPackagesController {
	
	@Autowired
	GetPackagesService getPackagesService;
	
	@RequestMapping(value="/packages/getPackagesNear/{location}")
	 public List<PackagesDriverHistory> getPackages(@PathVariable String location,@RequestHeader("Authorization") String token) throws IOException {
		 return getPackagesService.getPackages(location,token);
	 }
	
}
