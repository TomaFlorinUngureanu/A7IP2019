package com.packages.getPackagesDriver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packages.exceptions.UnknownMatchException;
import com.packages.model.JwtUser;
import com.packages.model.PackagesDriverHistory;
import com.packages.model.PackagesSenderHistory;
import com.packages.repositories.CommandsHistoryRepository;

@Service
public class GetPackagesService {
	
	@Autowired
	private CommandsHistoryRepository commandsHistoryRepository;
	
	public List<PackagesDriverHistory> getPackages(String location,String token) throws IOException {
		
		BufferedReader reader1 = null ;
		URL url1 = new URL("http://localhost:8297/trip/verifyLocation/"+location.replace(" ","+"));
	    HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();
	    
	    con1.setRequestMethod("GET");
	    con1.setRequestProperty("Content-Type", "application/json");	    
        con1.setRequestProperty("Authorization", token);
        
	    con1.setDoInput(true);
		reader1 = new BufferedReader(new InputStreamReader(con1.getInputStream()));
		String json1 = reader1.lines().collect(Collectors.joining("\n"));
		Boolean adresaValida1= Boolean.parseBoolean(json1);
		
		if(!adresaValida1)  throw new UnknownMatchException("Current location is not valid");
		
		List<PackagesSenderHistory> cmd= new ArrayList<PackagesSenderHistory>();
		List<PackagesDriverHistory> result= new ArrayList<PackagesDriverHistory>();
		cmd=commandsHistoryRepository.findAll();
	   for(PackagesSenderHistory i:cmd) {
		if(i.getStatus().equals("Ready")) continue;
		if(i.getEmailDriver().equals(JwtUser.getUserName())) continue;
		BufferedReader reader = null ;
		URL url = new URL("http://localhost:8297/trip/getDistance/"+location.replace(" ","+")+"/"+i.getSenderAdress().replace(" ","+"));
	    HttpURLConnection con = (HttpURLConnection) url.openConnection();
	    
	    con.setRequestMethod("GET");
	    con.setRequestProperty("Content-Type", "application/json");	    
        con.setRequestProperty("Authorization", token);
        
	    con.setDoInput(true);
		reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String json = reader.lines().collect(Collectors.joining("\n"));
		Double distance= Double.parseDouble(json);

		if(distance<4) result.add(new PackagesDriverHistory(i));
	   }
		return result;
	}	
	
}
