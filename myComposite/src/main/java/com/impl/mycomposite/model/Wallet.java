package com.impl.mycomposite.model;

import java.util.Set;

public class Wallet {

	int walletId;
	int customerId;
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
