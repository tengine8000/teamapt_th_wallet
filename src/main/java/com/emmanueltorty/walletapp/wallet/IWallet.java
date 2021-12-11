package com.emmanueltorty.walletapp.wallet;

import java.math.BigDecimal;

public interface IWallet {

	BigDecimal deposit(BigDecimal amount);
	BigDecimal withdraw(BigDecimal amount);
	BigDecimal transfer(String sourceWalletID, String destWalletID, BigDecimal amount);
}
