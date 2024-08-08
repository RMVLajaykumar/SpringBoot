package com.monocept.app.dto;



import java.time.LocalDateTime;

import com.monocept.app.entity.Account;
import com.monocept.app.entity.TransactionType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class TransactionRequestDto {
	
	private long id;
	
	
	private Account senderAccount;
	
	
	private Account receiverAccount;
	
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	
	private LocalDateTime transactionDate;
	
	@NotNull
	private double amount;

}