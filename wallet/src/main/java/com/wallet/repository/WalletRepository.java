package com.wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer>{
	List<Wallet> findByCustomerId(Integer customerId);
}
