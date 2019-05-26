package com.packages.history;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packages.exceptions.UnknownMatchException;
import com.packages.model.JwtUser;
import com.packages.model.PackagesDriverHistory;
import com.packages.model.PackagesSenderHistory;
import com.packages.repositories.CommandsHistoryRepository;

@Service
public class PackagesHistoryService {

	@Autowired
	private CommandsHistoryRepository cmdHistRepo;
	
	public List<PackagesDriverHistory> gePackagesHistoryDriver() throws UnknownMatchException {
		if(!cmdHistRepo.existsByEmailDriver(JwtUser.getUserName())) throw new UnknownMatchException("You don't have any package delivered");
		List<PackagesDriverHistory> list=new ArrayList<PackagesDriverHistory>();
		List<PackagesSenderHistory> packages=new ArrayList<PackagesSenderHistory>();
		packages=cmdHistRepo.findAllByEmailDriver(JwtUser.getUserName());
		for(PackagesSenderHistory i : packages) {
			if(!i.getStatus().equals("Delivered")) continue;
			list.add(new PackagesDriverHistory(i));
		}
		return list;
		
	}

	public List<PackagesSenderHistory> gePackagesHistorySender() {
		if(!cmdHistRepo.existsByEmailSender(JwtUser.getUserName())) throw new UnknownMatchException("You don't have any package placed");
		return cmdHistRepo.findAllByEmailSender(JwtUser.getUserName());
	}
	
	public PackagesSenderHistory getPackageSender(int id) {
		PackagesSenderHistory cmd=new PackagesSenderHistory();
		
		if(!cmdHistRepo.existsById(id)) throw new UnknownMatchException("The package doesn't exists");
		
		cmd=cmdHistRepo.findById(id).get();
		
		return cmd;
	}

	public List<PackagesDriverHistory> getPackagesNotDeliveredDriver() {
		if(!cmdHistRepo.existsByEmailDriver(JwtUser.getUserName())) throw new UnknownMatchException("You don't have any package accepted or in delivery");
		List<PackagesDriverHistory> list=new ArrayList<PackagesDriverHistory>();
		List<PackagesSenderHistory> packages=new ArrayList<PackagesSenderHistory>();
		packages=cmdHistRepo.findAllByEmailDriver(JwtUser.getUserName());
		for(PackagesSenderHistory i : packages) {
			if(!i.getStatus().equals("Accepted") || !i.getStatus().equals("In Delivery")) continue;
			list.add(new PackagesDriverHistory(i));
		}
		return list;
	}

}
