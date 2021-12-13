package com.emmanueltorty.walletapp.wallet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emmanueltorty.walletapp.exceptions.WalletException;
import com.emmanueltorty.walletapp.user.User;

@Service
@Transactional
public class WalletService implements IWallet {
	
	@Autowired
	private WalletRepository walletRepo;

	
	public Wallet createUserWallet(String ownerID) throws WalletException
	{
		
		//Check if wallet already exists
		Optional<Wallet> existingWallet = walletRepo.findByOwnerID(ownerID);
		
		if (existingWallet.isPresent()) 
			throw new WalletException("Wallet already exists for user!");
			
		// create wallet for user	
		Wallet wallet = new Wallet();
		wallet.setOwnerID(ownerID);
		wallet.setBalance(new BigDecimal("0"));
		
		return walletRepo.save(wallet);
	}
	

	@Override
	public BigDecimal deposit(BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal withdraw(BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal transfer(String sourceWalletID, String destWalletID, BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}


	public BigDecimal getUserWalletBalance(String ownerID) throws WalletException
	{
		Optional<Wallet> existingWallet = walletRepo.findByOwnerID(ownerID);
		if (existingWallet.isPresent()) 
			return existingWallet.get().getBalance();
		else
			throw new WalletException("User has no wallet!");
	}


	public BigDecimal depositUserWallet(String ownerID, String amount) throws WalletException
	{
		Optional<Wallet> Owallet = walletRepo.findByOwnerID(ownerID);
		BigDecimal zeroAmount = new BigDecimal("0");
		Wallet wallet = null;
		
		if(Owallet.isPresent()) {
			wallet = Owallet.get();
			BigDecimal depositAmount = new BigDecimal(amount).setScale(2, RoundingMode.DOWN);
			if(depositAmount.compareTo(zeroAmount) <= 0) 
			{
				throw new WalletException("Deposit cannot be zero or negative!");
			}
			
			walletRepo.UpdateWalletByOwnerID(ownerID, wallet.getBalance().add(depositAmount));
		}
		return wallet.getBalance();
	}


}
