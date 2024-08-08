package com.monocept.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.monocept.app.dto.AccountRequestDto;
import com.monocept.app.dto.AccountResponseDto;
import com.monocept.app.dto.CustomerRequestDto;
import com.monocept.app.dto.CustomerResponseDto;
import com.monocept.app.dto.TransactionResponseDto;
import com.monocept.app.util.PagedResponse;

public interface BankService {

	PagedResponse<CustomerResponseDto> getAllCustomers(int page, int size, String sortBy, String direction);

	CustomerResponseDto addCustomer(CustomerRequestDto customerrequestdto);

	String deleteCustomerById(long id);

	CustomerResponseDto findCustomerByid(long id);

	CustomerResponseDto addAccount(long cid, int bid);

	TransactionResponseDto doTransaction(long senderAccountno, long receiverAccountno, double amount);

	PagedResponse<TransactionResponseDto> viewAllTransaction(int page, int size, String sortBy, String direction);

	List<TransactionResponseDto> viewPassbook(long accountNo);

	List<TransactionResponseDto> searchByDate(LocalDateTime fromDate, LocalDateTime toDate);

}
