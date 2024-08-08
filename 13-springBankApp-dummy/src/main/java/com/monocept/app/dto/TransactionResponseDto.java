package com.monocept.app.dto;



import java.time.LocalDateTime;

import com.monocept.app.entity.Account;
import com.monocept.app.entity.TransactionType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class TransactionResponseDto {
	
	private long id;
	
	
	private AccountResponseDto senderAccount;
	 
	
	
	private AccountResponseDto receiverAccount;
	

	private TransactionType transactionType;
	
	
	private LocalDateTime transactionDate;

	private double amount;

}