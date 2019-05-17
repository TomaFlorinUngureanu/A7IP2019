package com.accountManagement.addCards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accountManagement.model.Cards;


@CrossOrigin("http://localhost:4200")
@RestController
public class AddCardController {

	@Autowired
	AddCardService addCardService;
	
	@RequestMapping(method=RequestMethod.POST,value="/accountManagement/addCard")
	  public String addCard(@RequestBody Cards card) {
		  return addCardService.addCard(card);
	  }
	
}
