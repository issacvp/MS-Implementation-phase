package com.impl.mycomposite.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Card {
	private int cardId;
	private String pan;
	private Date expiry;
	private String cvv;

	@JsonIgnore
    private Wallet wallet;

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public Card(int cardId, String pan, Date expiry, String cvv, Wallet wallet) {
		super();
		this.cardId = cardId;
		this.pan = pan;
		this.expiry = expiry;
		this.cvv = cvv;
		this.wallet = wallet;
	}

	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Card [cardId=" + cardId + ", pan=" + pan + ", expiry=" + expiry + ", cvv=" + cvv + ", wallet=" + wallet
				+ "]";
	}
	
	
}
