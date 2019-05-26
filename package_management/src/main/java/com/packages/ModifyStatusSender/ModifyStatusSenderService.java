package com.packages.ModifyStatusSender;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packages.exceptions.UnknownMatchException;
import com.packages.model.JwtUser;
import com.packages.model.ModifyPackageInformation;
import com.packages.model.PackagesSenderHistory;
import com.packages.repositories.CommandsHistoryRepository;

@Service
public class ModifyStatusSenderService {

	@Autowired
	private CommandsHistoryRepository cmdHistRepo;
	
	public String changePackage(ModifyPackageInformation form) throws JSONException {
		
		if(form.getId()==0) throw new UnknownMatchException("Invalid data");
		
		if(!cmdHistRepo.existsById(form.getId())) throw new UnknownMatchException("Invalid package id");
		
		PackagesSenderHistory cmd= new PackagesSenderHistory();
		cmd=cmdHistRepo.findById(form.getId()).get();
		
		if(!cmd.getEmailSender().equals(JwtUser.getUserName())) throw new UnknownMatchException("You can't edit the status of a package that hasn't been placed by you");
		
		if(!cmd.getStatus().equals("Accepted")) throw new UnknownMatchException("You can't change the status of a package that doesn't have Accepted status");
		
	    cmd.setEmailDriver(null);
	    cmd.setStatus("Ready");
		
		cmdHistRepo.save(cmd);
		JSONObject res = new JSONObject();
		res.put("message", "Success");
		return res.toString();
	}
	
}
