package com.emmanueltorty.walletapp.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emmanueltorty.walletapp.exceptions.*;


@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public UserService() {
		
	}
	
	public UserService(UserRepository userRepo) 
	{
		this.userRepo = userRepo;
	}
	
	public @ResponseBody String registerNewUser(User user) throws UniqueFieldException
	{
		
		Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
		
		if(existingUser.isPresent()) throw new UniqueFieldException("Email Taken!");
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userRepo.save(user);
		return "New User " + user.getName() + " with email, "+ user.getEmail()+", successfully created;";
	}

}
