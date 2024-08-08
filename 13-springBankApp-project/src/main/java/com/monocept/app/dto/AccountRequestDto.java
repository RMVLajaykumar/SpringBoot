package com.monocept.app.dto;



import java.util.List;

import com.monocept.app.entity.Bank;
import com.monocept.app.entity.Customer;
import com.monocept.app.entity.Transaction;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class AccountRequestDto {
	
	
	private long accountNumber;

	
	private BankRequestDto bankrequestDto;

	
	private CustomerRequestDto customerRequestDto;

	private List<TransactionRequestDto> sentTransactions;
	
	private List<TransactionRequestDto> receiverTransactions;

	@NotNull
	private double balance;


}