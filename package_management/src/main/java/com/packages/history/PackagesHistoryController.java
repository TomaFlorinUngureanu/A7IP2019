package com.packages.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packages.model.PackagesDriverHistory;
import com.packages.model.PackagesSenderHistory;

@RestController

public class PackagesHistoryController {
	
	@Autowired
	private PackagesHistoryService packagesHistoryService;
	
	 @RequestMapping("/packages/getPackagesHistoryDriver")
	 public ResponseEntity<List<PackagesDriverHistory>> getPackagesHistoryDriver()  {
		 return ResponseEntity.ok(packagesHistoryService.gePackagesHistoryDriver());
	 }
	 
	 @RequestMapping("/packages/getPackagesNotDeliveredDriver")
	 public ResponseEntity<List<PackagesDriverHistory>> getPackagesNotDeliveredDriver()  {
		 return ResponseEntity.ok(packagesHistoryService.getPackagesNotDeliveredDriver());
	 }
	 
	 @RequestMapping("/packages/getPackagesSender")
	 public ResponseEntity<List<PackagesSenderHistory>> getPackagesHistorySender()  {
		 return ResponseEntity.ok(packagesHistoryService.gePackagesHistorySender());
	 }
	 
	 @RequestMapping("/packages/sender/one/{id}")
	 public PackagesSenderHistory getPackagesSender(@PathVariable int id) {
		 return packagesHistoryService.getPackageSender(id);
	 }
}
