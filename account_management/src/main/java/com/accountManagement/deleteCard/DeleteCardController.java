package com.accountManagement.deleteCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
@RestController
public class DeleteCardController {

	@Autowired
	DeleteCardService deleteCardService;
	
	@RequestMapping(method=RequestMethod.DELETE,value="/accountManagement/deleteCard/{cardNumber}")
	  public String deleteCard(@PathVariable String cardNumber) {
		  return deleteCardService.deleteCard(cardNumber);
	  }
	
}
