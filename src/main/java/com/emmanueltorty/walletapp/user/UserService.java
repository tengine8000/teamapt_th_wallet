package com.emmanueltorty.walletapp.user;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emmanueltorty.walletapp.exceptions.UniqueFieldException;
import com.emmanueltorty.walletapp.exceptions.WalletException;
import com.emmanueltorty.walletapp.jwtsecurity.AuthenticationRequest;
import com.emmanueltorty.walletapp.jwtsecurity.AuthenticationResponse;
import com.emmanueltorty.walletapp.jwtsecurity.MyUserDetailsService;
import com.emmanueltorty.walletapp.jwtsecurity.util.JwtUtil;
import com.emmanueltorty.walletapp.wallet.Wallet;
import com.emmanueltorty.walletapp.wallet.WalletService;
import com.emmanueltorty.walletapp.exceptions.ResourceNotFoundException;


@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authMgr;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
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
	
	public ResponseEntity<?> authenticateUser(AuthenticationRequest authReq) throws Exception
	{
		try {
			
			authMgr.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
		}
		
		catch (Exception e) {
			throw new Exception("Authentication error! Please check your email or password");
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authReq.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt, "Authenticated!"));
	}
	
	public User getUserFromToken(HttpServletRequest req) 
	{
		String token = req.getHeader("Authorization").replace("Bearer ","");
		return userRepo.findByEmail(jwtUtil.extractUsername(token)).get();
	}
	
	public ResponseEntity<?> makeUserWallet(HttpServletRequest req) throws WalletException 
	{
		User user = this.getUserFromToken(req);
		return ResponseEntity.ok(this.walletService.createUserWallet(String.valueOf(user.getId())));
	}
		

}
