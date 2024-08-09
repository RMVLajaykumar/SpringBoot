package com.monocept.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.monocept.app.dto.AccountRequestDto;
import com.monocept.app.dto.AccountResponseDto;
import com.monocept.app.dto.CustomerRequestDto;
import com.monocept.app.dto.CustomerResponseDto;
import com.monocept.app.dto.ProfileRequestDto;
import com.monocept.app.dto.TransactionResponseDto;
import com.monocept.app.dto.UserRequestDto;
import com.monocept.app.dto.UserResponseDto;
import com.monocept.app.util.PagedResponse;

public interface BankService {

	PagedResponse<CustomerResponseDto> getAllCustomers(int page, int size, String sortBy, String direction);

	

	//String deleteCustomerById(long id);

	CustomerResponseDto findCustomerByid(long id);

	CustomerResponseDto addAccount(long cid, int bid);

	TransactionResponseDto doTransaction(long senderAccountno, long receiverAccountno, double amount);

	PagedResponse<TransactionResponseDto> viewAllTransaction(LocalDateTime fromDate, LocalDateTime toDate, int page, int size, String sortBy, String direction);

	PagedResponse<TransactionResponseDto> viewPassbook(long accountNo, LocalDateTime fromDate, LocalDateTime toDate, int page, int size, String sortBy, String direction);

	UserResponseDto createCustomer(CustomerRequestDto customerRequestDto, long userID);

	String updateProfile(ProfileRequestDto profileRequestDto);

	AccountResponseDto depositAmount(long accountNumber, double amount);
	
	List<AccountResponseDto> getAccounts();



	String activateCustomer(long customerID);



	String deleteAccount(long accountNumber);



	String activateAccount(long accountNumber);



	AccountResponseDto viewBalance(long accountNumber);



	String deleteCustomer(long customerID);
}
