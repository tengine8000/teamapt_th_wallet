package com.emmanueltorty.walletapp.exceptions;

import java.io.Serializable;

public class UniqueFieldException extends Exception implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UniqueFieldException(String message) {
		super(message);
	}
}
