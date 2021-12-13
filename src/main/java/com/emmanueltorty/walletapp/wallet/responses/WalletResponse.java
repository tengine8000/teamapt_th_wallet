package com.emmanueltorty.walletapp.wallet.responses;

import java.math.BigDecimal;

public class WalletResponse {
	
	private String availableBalance;
	private String message;
	
	
	public WalletResponse() {
		super();
	}

	public WalletResponse(String availableBal, String message) {
		super();
		this.availableBalance = availableBal;
		this.message = message;
	}

	public String getAvailableBalance() {
		return availableBalance;
	}
	
	public void setAvailableBalance(String availableBal) {
		this.availableBalance = availableBal;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
