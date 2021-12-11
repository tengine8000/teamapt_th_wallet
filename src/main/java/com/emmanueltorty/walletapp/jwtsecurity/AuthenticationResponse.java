package com.emmanueltorty.walletapp.jwtsecurity;

public class AuthenticationResponse {

	private final String jwt;
	private String message;


	public AuthenticationResponse(String jwt, String message) {
		super();
		this.jwt = jwt;
		this.message = message;
	}
	
	public String getJwt() {
		return jwt;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}