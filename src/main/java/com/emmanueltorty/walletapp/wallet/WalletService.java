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
public class WalletService {
	
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
		Wallet wallet = this.getUserWallet(ownerID);
		BigDecimal zeroAmount = new BigDecimal("0");
		BigDecimal depositAmount = new BigDecimal(amount).setScale(2, RoundingMode.DOWN);
		if(depositAmount.compareTo(zeroAmount) <= 0) 
		{
			throw new WalletException("Deposit cannot be zero or negative!");
		}
			
		walletRepo.UpdateWalletByOwnerID(ownerID, wallet.getBalance().add(depositAmount));
		return this.getUserWallet(ownerID).getBalance();
	}


	public BigDecimal withdrawUserWallet(String ownerID, String amount) throws WalletException
	{
		Wallet wallet = this.getUserWallet(ownerID);
		BigDecimal withdrawAmount = new BigDecimal(amount).setScale(2, RoundingMode.DOWN);
		BigDecimal zeroAmount = new BigDecimal("0").setScale(2, RoundingMode.DOWN);
		
		if (withdrawAmount.compareTo(zeroAmount) <= 0) {
			throw new WalletException("Invalid amount to withdraw!");
		}
		
		if (wallet.getBalance().compareTo(withdrawAmount) < 0) {
			throw new WalletException("Insufficient funds to withdraw!");
		}
		
		walletRepo.UpdateWalletByOwnerID(ownerID, wallet.getBalance().subtract(withdrawAmount));
		return this.getUserWallet(ownerID).getBalance();
	}
	
	public BigDecimal transferUserWallet(String ownerID, String receiverID, String amount) 
			throws WalletException
	{
		Wallet userWallet = this.getUserWallet(ownerID);
		Wallet receipientWallet = this.getUserWallet(receiverID);
		BigDecimal transferAmount = new BigDecimal(amount).setScale(2, RoundingMode.DOWN);
		BigDecimal zeroAmount = new BigDecimal("0").setScale(2, RoundingMode.DOWN);
		
		if (transferAmount.compareTo(zeroAmount) <= 0) {
			throw new WalletException("Invalid amount to transfer!");
		}
		
		if (userWallet.getBalance().compareTo(transferAmount) < 0) {
			throw new WalletException("Insufficient funds to transfer!");
		}
		
		if (userWallet.getId() == receipientWallet.getId()) {
			throw new WalletException("Error! Receiver ID same as your ID. You may use /deposit API endpoint to fund your wallet!");
		}
		
		walletRepo.UpdateWalletByOwnerID(userWallet.getOwnerID(), userWallet.getBalance().subtract(transferAmount));
		walletRepo.UpdateWalletByOwnerID(receipientWallet.getOwnerID(), receipientWallet.getBalance().add(transferAmount));
		
		return this.getUserWallet(ownerID).getBalance();	
	}
	
	private Wallet getUserWallet(String ownerID) throws WalletException
	{
		Optional<Wallet> Owallet = walletRepo.findByOwnerID(ownerID);
		Wallet wallet = null;
		if(Owallet.isPresent()) {
			wallet = Owallet.get();
					
		}else {
			throw new WalletException("Wallet not found for user!");
		}
		
		return wallet;
	}




}
