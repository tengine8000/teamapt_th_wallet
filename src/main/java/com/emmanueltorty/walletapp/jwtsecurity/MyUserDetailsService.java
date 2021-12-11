package com.emmanueltorty.walletapp.jwtsecurity;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emmanueltorty.walletapp.user.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<com.emmanueltorty.walletapp.user.User> user = userRepo.findByEmail(email);
		
		if(user.isPresent()) {
			com.emmanueltorty.walletapp.user.User dbUser = user.get();
			return new org.springframework.security.core.userdetails.User(
					dbUser.getEmail(), dbUser.getPassword(), new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User with email " + email + " not found");
		}
	}

}