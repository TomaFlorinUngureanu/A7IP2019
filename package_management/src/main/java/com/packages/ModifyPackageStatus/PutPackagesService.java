package com.packages.ModifyPackageStatus;

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
public class PutPackagesService {

	@Autowired
	private CommandsHistoryRepository cmdHistRepo;
	
	public String changePackage(ModifyPackageInformation form) throws JSONException {
		
		if(form.getId()==0 || form.getStatus()==null) throw new UnknownMatchException("Invalid body data");
		
		if(!form.getStatus().equals("Accepted") && !form.getStatus().equals("In Delivery") && !form.getStatus().equals("Delivered")) 
			throw new UnknownMatchException("Invalid status");
		
		PackagesSenderHistory cmd= new PackagesSenderHistory();
		cmd=cmdHistRepo.findById(form.getId()).get();
		
		//if(cmd.getEmailSender().equals(JwtUser.getUserName())) throw new UnknownMatchException("You can't seend a package to yourself");
		
		
		if(cmd.getEmailDriver()==null && form.getStatus().equals("Accepted")) {
			cmd.setEmailDriver(JwtUser.getUserName());
			cmd.setStatus(form.getStatus());
		}
		else {
			if(cmd.getEmailDriver()==null) throw new UnknownMatchException("Invalid data");
			if(cmd.getEmailDriver().equals(JwtUser.getUserName()) && form.getStatus().equals("In Delivery")) {
				cmd.setStatus(form.getStatus());
			}else if(cmd.getEmailDriver().equals(JwtUser.getUserName()) && form.getStatus().equals("Delivered"))
			{
				PackagesSenderHistory packageByPin= new PackagesSenderHistory();
				
				if( form.getPin()==0) throw new UnknownMatchException("You didn't fill pin field");
				
				if(!cmdHistRepo.existsByPin(form.getPin())) throw new UnknownMatchException("The given pin doesn't exists");
				
				packageByPin=cmdHistRepo.findByPin(form.getPin()).get();
				if(!packageByPin.getEmailDriver().equals(JwtUser.getUserName()) && packageByPin.getId()!=form.getId()) 
					throw new UnknownMatchException("The given pin isn't correct");	
				   else cmd.setStatus(form.getStatus());
			}
			else throw new UnknownMatchException("Invalid data");
		}
		cmdHistRepo.save(cmd);
		JSONObject res = new JSONObject();
		res.put("message", "Success");
		return res.toString();
	}
}
