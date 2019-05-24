package com.packages.paymentEstimation;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:4200")
public class PackageController {
	@Autowired
	PackageService packageController;
	
	@RequestMapping("/packages/estimatePrice/{id}")
	public String Estimate(@PathVariable int id,@RequestHeader("Authorization") String token) throws IOException
	{
		return  packageController.EstimatePrice(id,token);
	}
}
