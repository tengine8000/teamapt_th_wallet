package com.emmanueltorty.walletapp.wallet.requests;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

@Validated
public class WalletTransferRequest {

	@NotEmpty(message="The receiverID field should not be null!")
	private String receiverID;
	
	@NotEmpty(message="The amount field should not be null!")
	private String amount;
	
	public WalletTransferRequest() {
		super();
	}
	
	public WalletTransferRequest(String receiverID, String amount) {
		super();
		this.receiverID = receiverID;
		this.amount = amount;
	}

	public String getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
