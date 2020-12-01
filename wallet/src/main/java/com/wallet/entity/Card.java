package com.wallet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "CARD")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_id")
	private int cardId;
	
	@Column(name = "pan")
	private String pan;
	
	@Column(name = "expiry")
	private Date expiry;
	
	@Column(name = "cvv")
	private String cvv;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "wallet_id", nullable = false)
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
