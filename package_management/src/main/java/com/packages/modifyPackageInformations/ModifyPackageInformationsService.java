package com.packages.modifyPackageInformations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packages.exceptions.UnknownMatchException;
import com.packages.model.JwtUser;
import com.packages.model.PackagesSenderHistory;
import com.packages.repositories.CommandsHistoryRepository;

@Service
public class ModifyPackageInformationsService {

	@Autowired
	private CommandsHistoryRepository cmdHistRepo;
	
	public String changePackage(PackagesSenderHistory form,String token) throws JSONException, IOException {
		
		if(form.getId()==null) throw new UnknownMatchException("You didn't specify package id");
		PackagesSenderHistory cmd=new PackagesSenderHistory();
		
		if(!cmdHistRepo.existsById(form.getId())) throw new UnknownMatchException("The package with the given id doesn't exist");
		cmd=cmdHistRepo.findById(form.getId()).get();
		
		
		if(!cmd.getStatus().equals("Ready"))  throw new UnknownMatchException("You can't modify informations for a package that doesn't have Ready status");
		if(!cmd.getEmailSender().equals(JwtUser.getUserName())) 
			throw new UnknownMatchException("You can't modify informations for a package that wasn't placed by you");
			
		if(form.getNamePackage()!=null) cmd.setNamePackage(form.getNamePackage());
		if(form.getSenderAdress()!=null)
			{
			BufferedReader reader = null ;
			URL url = new URL("http://localhost:8297/trip/verifyLocation/"+URLEncoder.encode(form.getSenderAdress(),"UTF-8"));
		    HttpURLConnection con = (HttpURLConnection) url.openConnection();
		    
		    con.setRequestMethod("GET");
		    con.setRequestProperty("Content-Type", "application/json");	    
	        con.setRequestProperty("Authorization", token);
	        
		    con.setDoInput(true);
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String json = reader.lines().collect(Collectors.joining("\n"));
			Boolean adresaValida= Boolean.parseBoolean(json);
			
			if(!adresaValida)  throw new UnknownMatchException("Sender address is not valid");
			
			cmd.setSenderAdress(form.getSenderAdress());
			}
		if(form.getReceiverAdress()!=null) {
			
			BufferedReader reader1 = null ;
			URL url1 = new URL("http://localhost:8297/trip/verifyLocation/"+URLEncoder.encode(form.getReceiverAdress(),"UTF-8"));
		    HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();
		    
		    con1.setRequestMethod("GET");
		    con1.setRequestProperty("Content-Type", "application/json");	    
	        con1.setRequestProperty("Authorization", token);
	        
		    con1.setDoInput(true);
			reader1 = new BufferedReader(new InputStreamReader(con1.getInputStream()));
			String json1 = reader1.lines().collect(Collectors.joining("\n"));
			Boolean adresaValida1= Boolean.parseBoolean(json1);
			
			if(!adresaValida1)  throw new UnknownMatchException("Receiver address is not valid");
			
			cmd.setReceiverAdress(form.getReceiverAdress());
		}
		if(form.getPhoneNumberSender()!=null) cmd.setPhoneNumberSender(form.getPhoneNumberSender());
		if(form.getSenderName()!=null) cmd.setSenderName(form.getSenderName());
		if(form.getPhoneNumberReceiver()!=null) cmd.setPhoneNumberReceiver(form.getPhoneNumberReceiver());
		if(form.getReceiverName()!=null) cmd.setReceiverName(form.getReceiverName());
		if(form.getKilograms()!=0) cmd.setKilograms(form.getKilograms());
		if(form.getLength()!=0) cmd.setLength(form.getLength());
		if(form.getWidth()!=0) cmd.setWidth(form.getWidth());
		if(form.getHeight()!=0) cmd.setHeight(form.getHeight());
		if(form.getSenderName()!=null) cmd.setSenderName(form.getSenderName());
		cmdHistRepo.save(cmd);
		
		JSONObject res = new JSONObject();
		res.put("message", "Success");
		return res.toString();
	}
	
}
