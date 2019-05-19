package com.packages.paymentEstimation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packages.exceptions.UnknownMatchException;
import com.packages.model.PackagesSenderHistory;
import com.packages.repositories.CommandsHistoryRepository;

import net.minidev.json.JSONObject;

@Service
public class PackageService {
	@Autowired
	CommandsHistoryRepository commandsHistoryRepository;
	
	public String EstimatePrice(int id,String token) throws IOException
	{   PackagesSenderHistory cmd= new PackagesSenderHistory();
	
	if(!commandsHistoryRepository.existsById(id)) throw new UnknownMatchException("The package with the given id doesn't exist");
	
	 	cmd= commandsHistoryRepository.findById(id).get();
	 	float kilograms=cmd.getKilograms();
	 	float volume=cmd.getHeight()*cmd.getLength()*cmd.getWidth();
		float weight;
		String address2=cmd.getReceiverAdress();
		String address1=cmd.getSenderAdress();
		float distance;
		
		BufferedReader reader = null ;
		URL url = new URL("http://localhost:8297/trip/getDistance/"+address1.replace(" ","+")+"/"+address2.replace(" ","+"));
	    HttpURLConnection con = (HttpURLConnection) url.openConnection();
	    
	    con.setRequestMethod("GET");
	    con.setRequestProperty("Content-Type", "application/json");	   
        con.setRequestProperty("Authorization", token); 
	   
	    con.setDoInput(true);
		reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String json = reader.lines().collect(Collectors.joining("\n"));
		distance= Float.parseFloat(json);
		
		float priceOf100km_h = 10*5.68f;  //litri de benzina la 100km * cost litru benzina
		if(kilograms>volume/6000) weight=kilograms;
		else weight=volume/6000;
		float price =0;
		float distancePrice = priceOf100km_h/100 * distance;
		price=weight*2.121f+ distancePrice;
		
		JSONObject jsonRet = new JSONObject();
		jsonRet.put("price", price);

		return jsonRet.toString();
	}
}
