package com.accountManagement.getCard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.accountManagement.model.Cards;

@CrossOrigin("http://localhost:4200")
@RestController
public class GetCardController {

	@Autowired
	GetCardService getCardService;
	
	@RequestMapping(value="/accountManagement/getCards")
	  public List<Cards> getCards() {
		  return getCardService.getCards();
	  }
}
