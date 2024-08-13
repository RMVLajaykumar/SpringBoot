package com.monocept.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.monocept.app.entity.Account;
import com.monocept.app.entity.Customer;

public interface AccountRepository extends JpaRepository<Account,Long>{

	
	@Query("select coalesce(sum(a.balance), 0) from Account a where a.customer = ?1")
	double getTotalBalance(Customer customer);

	Page<Account> findByCustomer(Customer customer, Pageable pageable);

}
