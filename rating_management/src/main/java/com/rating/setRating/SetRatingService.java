package com.rating.setRating;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rating.exceptions.UnknownMatchException;
import com.rating.model.JwtUser;
import com.rating.model.PackageSender;
import com.rating.model.RatingForm;
import com.rating.repositories.RatingRepository;

import net.minidev.json.JSONObject;

@Service
public class SetRatingService {
	@Autowired
	RatingRepository ratingRepo;

	public String setRating(RatingForm ratingForm,String token) throws IOException {
		
		if(ratingForm.getEmailDriver()!=null || ratingForm.getIdPackage()==0 || ratingForm.getRating()==0) {
			throw new UnknownMatchException("Invalid data");
		}
		
		if(ratingRepo.existsByIdPackage(ratingForm.getIdPackage())) throw new UnknownMatchException("A rating already exists for the given delivery");
		
		BufferedReader reader = null ;
		URL url = new URL("http://localhost:8296/packages/sender/one/"+ratingForm.getIdPackage());
	    HttpURLConnection con = (HttpURLConnection) url.openConnection();
	    
	    con.setRequestMethod("GET");
	    con.setRequestProperty("Content-Type", "application/json");	    
        con.setRequestProperty("Authorization", token);
        
	    con.setDoInput(true);
		reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String json = reader.lines().collect(Collectors.joining("\n"));
		
		 Gson gson = new Gson();
		 PackageSender cmd = gson.fromJson(json, PackageSender.class);
		 
		 if(!cmd.getEmailSender().equals(JwtUser.getUserName())) throw new UnknownMatchException("You can't rate a delivery of other user");
		
		 if(!cmd.getStatus().equals("Delivered")) throw new UnknownMatchException("You can't rate a delivery that doesn't have Delivered status");
		 
		 if(ratingForm.getRating()<1 || ratingForm.getRating()>10)  throw new UnknownMatchException("Rating must be a number between 1 and 10");
		 
		 ratingForm.setEmailDriver(cmd.getEmailDriver());
		 
		 ratingRepo.save(ratingForm);
		 
		JSONObject jsonRet = new JSONObject();
		jsonRet.put("message", "Success");
		return jsonRet.toString();
	}
	
	
	

}
