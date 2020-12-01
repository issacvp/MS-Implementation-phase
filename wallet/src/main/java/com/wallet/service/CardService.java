package com.wallet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.entity.Card;
import com.wallet.exception.NoMatchException;
import com.wallet.repository.CardRepository;

import javassist.NotFoundException;

@Service
public class CardService {

	@Autowired
	CardRepository cardRepo;
	
	public Card save(Card card) {
		card = cardRepo.save(card);
		return card;
	}
	
	public Card update(Card card) {
		return save(card);
	}
	
	public Card findBy(int cardId) throws NoMatchException{
		Card card = null;
		Optional<Card> opt = cardRepo.findById(cardId);
		if(opt.isPresent()) {
			card = opt.get();
			return card;
		} else {
			throw new NoMatchException("No matching card found");
		}
	}
	
	public void delete(Card card) {
		cardRepo.delete(card);
	}
}
