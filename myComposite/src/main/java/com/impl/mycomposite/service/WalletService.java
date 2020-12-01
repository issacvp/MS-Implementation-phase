package com.impl.mycomposite.service;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.impl.mycomposite.model.Card;
import com.impl.mycomposite.model.Wallet;

@Service
@FeignClient(name="WALLET-MICROSERVICE")
public interface WalletService {

	@GetMapping
	public List<Wallet> getWallets();
	
	@GetMapping("/{walletId}")
	public Wallet getWallet(@PathVariable Integer walletId);
	
	@GetMapping("/customer/{customerId}")
	public List<Wallet> getWalletsByCustomer(@PathVariable Integer customerId);
	
	@PostMapping
	public ResponseEntity<Wallet> save(@RequestBody Wallet wallet);
	
	@PutMapping("/{walletId}")
	public ResponseEntity<Wallet> update(@PathVariable Integer walletId, @RequestBody Wallet wallet);
	
	@GetMapping("/{walletId}/cards")
	public Set<Card> getCards(@PathVariable Integer walletId);
	
	@PostMapping("/{walletId}/cards")
	public ResponseEntity<Wallet> addCard(@PathVariable Integer walletId, @RequestBody Card card);
	
	@PutMapping("/{walletId}/cards/{cardId}")
	public ResponseEntity<Wallet> updateCard(@PathVariable Integer walletId, @PathVariable Integer cardId, @RequestBody Card card);
	
	@DeleteMapping("/{walletId}/cards/{cardId}")
	public ResponseEntity<Wallet> removeCard(@PathVariable Integer walletId, @PathVariable Integer cardId);
	
}
