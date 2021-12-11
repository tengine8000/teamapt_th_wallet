package com.emmanueltorty.walletapp.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {

	
	public Optional<User> findByEmail(String email);
}