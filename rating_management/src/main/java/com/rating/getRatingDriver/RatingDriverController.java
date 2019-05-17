package com.rating.getRatingDriver;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rating.model.RatingForm;
import com.rating.setRating.SetRatingService;

@CrossOrigin("http://localhost:4200")
@RestController
public class RatingDriverController {
	@Autowired
	RatingDriverService ratingService;
	
	@RequestMapping(value="/rating/getRating/{id}")
	public String getRating(@PathVariable String id) throws IOException {
		  return ratingService.getRating(id);
	  }
	
}
