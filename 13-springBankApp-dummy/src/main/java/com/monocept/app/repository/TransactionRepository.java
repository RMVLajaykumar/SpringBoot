package com.monocept.app.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monocept.app.entity.Account;
import com.monocept.app.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

	
	
	@Query("select t from Transaction t where (t.senderAccount = :account or t.receiverAccount = :account) and t.transactionDate between :from and :to")
	Page<Transaction> viewPassbook(@Param("account") Account account, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to, Pageable pageable);

	
	
	//List<Transaction> findByTransactionDateBetween(LocalDateTime fromDate, LocalDateTime toDate);



	Page<Transaction> findAllByTransactionDateBetween(LocalDateTime fromDate, LocalDateTime toDate,
			PageRequest pageRequest);

}
