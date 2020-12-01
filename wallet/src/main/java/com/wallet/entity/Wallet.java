package com.wallet.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "WALLET")
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wallet_id")
	int walletId;
	
	@Column(name = "customer_id")
	int customerId;
	
	@OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Card> cards;

	public Wallet(int walletId, int customerId, Set<Card> cards) {
		super();
		this.walletId = walletId;
		this.customerId = customerId;
		this.cards = cards;
	}

	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return "Wallet [walletId=" + walletId + ", customerId=" + customerId + ", cards=" + cards + "]";
	}
	
	
}
