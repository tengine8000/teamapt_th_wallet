package com.emmanueltorty.walletapp.wallet.requests;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

@Validated
public class WalletDepositRequest {
	
	@NotEmpty(message="The amount field should not be null!")
	private String amount;
	
	public WalletDepositRequest()
	{
		super();
	}
	
	public WalletDepositRequest(String amount)
	{
		super();
		this.setAmount(amount);
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
