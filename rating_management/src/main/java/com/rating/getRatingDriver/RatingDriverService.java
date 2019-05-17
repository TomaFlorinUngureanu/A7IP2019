package com.rating.getRatingDriver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.exceptions.UnknownMatchException;
import com.rating.model.RatingForm;
import com.rating.repositories.RatingRepository;

import net.minidev.json.JSONObject;

@Service
public class RatingDriverService {

	@Autowired
	RatingRepository ratingRepo;

	public String getRating(String email) {
	
		Float rating=(float) 0;
		
		if(!ratingRepo.existsByEmailDriver(email)) throw new UnknownMatchException("This driver doesn't have any rating yet");
		
		List<RatingForm> ratings =new ArrayList<RatingForm>();
		ratings=ratingRepo.findAllByEmailDriver(email);
		for(RatingForm i : ratings) {
			rating += i.getRating();
		}
		rating/=ratingRepo.countByEmailDriver(email);
		
		JSONObject jsonRet = new JSONObject();
		jsonRet.put("rating", rating);
		return jsonRet.toString();
	}

}
