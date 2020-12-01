package com.wallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.entity.Wallet;
import com.wallet.exception.NoMatchException;
import com.wallet.exception.OnlineException;
import com.wallet.repository.WalletRepository;

import javassist.NotFoundException;

@Service
public class WalletService {

	@Autowired
	WalletRepository walletRepo;

	public Wallet save(Wallet wallet) {
		wallet = walletRepo.save(wallet);
		return wallet;
	}

	public List<Wallet> findAll() {
		return walletRepo.findAll();
	}
	
	public List<Wallet> findAllByCustomer(int customerId) {
		return walletRepo.findByCustomerId(customerId);
	}

	public Wallet findBy(int walletId) throws NoMatchException{
		Wallet wallet = null;
		Optional<Wallet> opt = walletRepo.findById(walletId);
		if(opt.isPresent()) {
			wallet = opt.get();
			return wallet;
		} else {
			throw new NoMatchException("No matching wallet found");
		}
	}
	
	public Wallet update(Wallet wallet) {
		return save(wallet);
	}
	
	
	
}
