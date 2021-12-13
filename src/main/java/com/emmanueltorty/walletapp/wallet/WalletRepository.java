package com.emmanueltorty.walletapp.wallet;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface WalletRepository extends CrudRepository<Wallet, Long> {
	
	public Optional<Wallet> findByOwnerID(String ownerID);
	
	
	@Modifying(clearAutomatically=true, flushAutomatically=true)
	@Query("update Wallet w set w.balance = :balance where w.ownerID = :ownerID")
	public int UpdateWalletByOwnerID(@Param("ownerID") String ownerID, @Param("balance") BigDecimal balance);
	
}
