package com.emmanueltorty.walletapp.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

@Validated
@Entity
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;

  @NotNull
  @Size(min=5, message="Enter a minimum of 5 characters.")
  private String name;

  @Email
  private String email;
  
  @NotNull(message="Password cannot be null")
  @Size(min=8, message="Password must be at least 8 characters long!")
  private String password;

  public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public long getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}