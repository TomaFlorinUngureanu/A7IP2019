package com.rating.setRating;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rating.model.RatingForm;

@CrossOrigin("http://localhost:4200")
@RestController
public class SetRatingController {
	@Autowired
	SetRatingService ratingService;
	
	@RequestMapping(method=RequestMethod.POST,value="/rating/setRating")
	public String setRating(@RequestBody RatingForm ratingForm,@RequestHeader("Authorization") String token) throws IOException {
		  return ratingService.setRating(ratingForm,token);
	  }
}
