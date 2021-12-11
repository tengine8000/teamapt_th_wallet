package com.emmanueltorty.walletapp.wallet;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

//@Validated
@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@NotNull
	private String ownerID;

	private BigDecimal balance;

	public long getId() {
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}

	public String getOwnerID() 
	{
		return ownerID;
	}

	public void setOwnerID(String ownerID) 
	{
		this.ownerID = ownerID;
	}

	public BigDecimal getBalance() 
	{
		return balance;
	}

	public void setBalance(BigDecimal balance) 
	{
		this.balance = balance;
	}

}
