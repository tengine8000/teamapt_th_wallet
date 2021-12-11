package com.emmanueltorty.walletapp.exceptions;

import java.io.Serializable;

class ResourceNotFoundException extends Exception implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
