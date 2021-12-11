package com.emmanueltorty.walletapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@GetMapping("/")
	public String home()
	{
		return "Welcome to Wallet APP APIs";
	}
	
//	@PostMapping("/register")
//	public @ResponseBody String register(@Valid @RequestBody User user)
//	{
//		
//	}

}
