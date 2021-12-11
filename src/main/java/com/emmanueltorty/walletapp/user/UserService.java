package com.emmanueltorty.walletapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	
	public UserService() {
		
	}
	
	public UserService(UserRepository userRepo) 
	{
		this.userRepo = userRepo;
	}

}
