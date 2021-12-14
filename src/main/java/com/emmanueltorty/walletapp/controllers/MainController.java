package com.emmanueltorty.walletapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emmanueltorty.walletapp.exceptions.UniqueFieldException;
import com.emmanueltorty.walletapp.exceptions.WalletException;
import com.emmanueltorty.walletapp.jwtsecurity.AuthenticationRequest;
import com.emmanueltorty.walletapp.jwtsecurity.AuthenticationResponse;
import com.emmanueltorty.walletapp.jwtsecurity.util.JwtUtil;
import com.emmanueltorty.walletapp.user.User;
import com.emmanueltorty.walletapp.user.UserService;
import com.emmanueltorty.walletapp.user.responses.UserProfileResponse;
import com.emmanueltorty.walletapp.wallet.Wallet;
import com.emmanueltorty.walletapp.wallet.requests.WalletDepositRequest;
import com.emmanueltorty.walletapp.wallet.requests.WalletTransferRequest;
import com.emmanueltorty.walletapp.wallet.requests.WalletWithdrawRequest;
import com.emmanueltorty.walletapp.wallet.responses.WalletResponse;


@RestController
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String home()
	{
		return "Welcome to Wallet APP APIs";
	}
	
	@PostMapping("/register")
	public @ResponseBody String register(@Valid @RequestBody User user) throws Exception
	{
		return this.userService.registerNewUser(user);	
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authReq) 
			throws Exception 
	{
		return this.userService.authenticateUser(authReq);
	}
	
	@GetMapping("/me")
	public @ResponseBody UserProfileResponse createWallet(HttpServletRequest req) 
			throws Exception {
		User user = this.userService.getUserFromToken(req);
		return new UserProfileResponse(user.getId(), user.getName(), user.getEmail());
	}
	
	@PostMapping("/create_wallet")
	public ResponseEntity<?> makeWallet(HttpServletRequest req) 
			throws WalletException {
		return this.userService.makeUserWallet(req);
	}
	
	@GetMapping("/get_wallet_balance")
	public @ResponseBody WalletResponse getWalletBalance(HttpServletRequest req) 
			throws WalletException {
		return new WalletResponse(this.userService.getWalletBalance(req).toString(), "Available Balance");
	}
	
	@PostMapping("/deposit")
	public @ResponseBody WalletResponse depositWallet(HttpServletRequest req, @Valid @RequestBody WalletDepositRequest wdr) 
			throws WalletException {
		
		if (wdr == null) {
			throw new WalletException("You have not supplied the amount to deposit!");
		}
		
		return new WalletResponse(this.userService.depositWallet(req, wdr.getAmount()).toString(), "Amount Deposited successfully!");
	}
	
	@PostMapping("/withdraw")
	public @ResponseBody WalletResponse withdrawWallet(HttpServletRequest req, @Valid @RequestBody WalletWithdrawRequest wwr) 
			throws WalletException {
		
		if (wwr == null) {
			throw new WalletException("You have not supplied the amount to deposit!");
		}
		
		return new WalletResponse(this.userService.withdrawWallet(req, wwr.getAmount()).toString(), "Amount Withdrawn successfully!");
	}
	
	@PostMapping("/transfer")
	public @ResponseBody WalletResponse transferWallet(HttpServletRequest req, @Valid @RequestBody WalletTransferRequest wtr) 
			throws WalletException {
		
		if (wtr == null) {
			throw new WalletException("You have not supplied the amount to deposit!");
		}
		
		return new WalletResponse(
				this.userService.transferWallet(
							req, wtr.getReceiverID(), 
							wtr.getAmount().toString()).toString(), 
				"Amount Transferred successfully!");
	}

}
