package com.accountManagement.getCard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountManagement.exceptions.UnknownMatchException;
import com.accountManagement.model.Cards;
import com.accountManagement.model.JwtUser;
import com.accountManagement.repositories.CardsRepository;

@Service
public class GetCardService {

	@Autowired
	private CardsRepository cardsRepo;

	public List<Cards> getCards() {
		List<Cards> cards = new ArrayList<Cards>();
		
		if(!cardsRepo.existsByEmailSender(JwtUser.getUserName())) throw new UnknownMatchException ("You don't have any card registered");
		
		cards=cardsRepo.findAllByEmailSender(JwtUser.getUserName());
		
		return cards;
	}

	
	
	
}
