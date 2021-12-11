package com.emmanueltorty.walletapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emmanueltorty.walletapp.exceptions.UniqueFieldException;
import com.emmanueltorty.walletapp.user.User;
import com.emmanueltorty.walletapp.user.UserService;

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
	public @ResponseBody String register(@Valid @RequestBody User user) throws UniqueFieldException
	{
		return this.userService.registerNewUser(user);
		
	}

}
