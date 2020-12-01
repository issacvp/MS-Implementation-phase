package com.wallet.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.entity.Card;
import com.wallet.entity.Wallet;
import com.wallet.exception.NoMatchException;
import com.wallet.exception.OnlineException;
import com.wallet.service.CardService;
import com.wallet.service.WalletService;

@RefreshScope
@RestController
public class WalletController {
	private static final Logger logger = LoggerFactory.getLogger(WalletController.class);

	@Autowired
	WalletService walletService;
	@Autowired
	CardService cardService;

	@GetMapping
	public List<Wallet> getWallets() {
		return walletService.findAll();
	}

	@GetMapping("/{walletId}")
	public Wallet getWallet(@PathVariable Integer walletId) {
		try {
			Wallet wallet = walletService.findBy(walletId);
			return wallet;
		} catch (NoMatchException e) {
			throw new NoMatchException("Wallet not found!");
		}
	}
	
	@GetMapping("/customer/{customerId}")
	public List<Wallet> getWalletsByCustomer(@PathVariable Integer customerId) {
		return walletService.findAllByCustomer(customerId);
	}

	@PostMapping
	public ResponseEntity<Wallet> save(@RequestBody Wallet wallet) {
		try {
			wallet = walletService.save(wallet);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OnlineException("Unknown error happened");
		}
		return new ResponseEntity<Wallet>(wallet, HttpStatus.CREATED);
	}

	@PutMapping("/{walletId}")
	public ResponseEntity<Wallet> update(@PathVariable Integer walletId, @RequestBody Wallet wallet) {
		try {
			Wallet wlt = walletService.findBy(walletId);
			wlt.setCustomerId(wallet.getCustomerId());
			wallet = walletService.update(wlt);
			return new ResponseEntity<Wallet>(wallet, HttpStatus.ACCEPTED);
			
		} catch (NoMatchException e) {
			throw new NoMatchException("Wallet not found!");
		}
	}
	
	@GetMapping("/{walletId}/cards")
	public Set<Card> getCards(@PathVariable Integer walletId) {
		try {
			Wallet wallet = walletService.findBy(walletId);
			return wallet.getCards();
			
		} catch (NoMatchException e) {
			throw new NoMatchException("Wallet not found!");
		}
	}
	
	@PostMapping("/{walletId}/cards")
	public ResponseEntity<Wallet> addCard(@PathVariable Integer walletId, @RequestBody Card card) {
		try {
			Wallet wallet = walletService.findBy(walletId);
			card.setWallet(wallet);
			card = cardService.save(card);
			wallet.getCards().add(card);
			wallet = walletService.update(wallet);
			return new ResponseEntity<Wallet>(wallet, HttpStatus.CREATED);
			
		} catch (NoMatchException e) {
			throw new NoMatchException("Wallet not found!");
		}
	}
	
	@PutMapping("/{walletId}/cards/{cardId}")
	public ResponseEntity<Wallet> updateCard(@PathVariable Integer walletId, @PathVariable Integer cardId, @RequestBody Card card) {
		try {
			Wallet wallet = walletService.findBy(walletId);
			Card crd = cardService.findBy(cardId);
			crd.setPan(card.getPan());
			crd.setExpiry(card.getExpiry());
			crd.setCvv(card.getCvv());
			card = cardService.save(crd);
			wallet.getCards().add(card);
			wallet = walletService.update(wallet);
			return new ResponseEntity<Wallet>(wallet, HttpStatus.CREATED);
			
		} catch (NoMatchException e) {
			throw new NoMatchException("Wallet or Card not found!");
		}
	}
	
	@DeleteMapping("/{walletId}/cards/{cardId}")
	public ResponseEntity<Wallet> removeCard(@PathVariable Integer walletId, @PathVariable Integer cardId) {
		try {
			Wallet wallet = walletService.findBy(walletId);
			Card card = cardService.findBy(cardId);
			cardService.delete(card);
			wallet.getCards().remove(card);
			wallet = walletService.update(wallet);
			return new ResponseEntity<Wallet>(wallet, HttpStatus.ACCEPTED);
			
		} catch (NoMatchException e) {
			throw new NoMatchException("Wallet or Card not found!");
		}
	}
}
