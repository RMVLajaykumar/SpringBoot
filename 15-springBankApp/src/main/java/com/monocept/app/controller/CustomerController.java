package com.monocept.app.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.monocept.app.dto.AccountResponseDto;
import com.monocept.app.dto.ProfileRequestDto;
import com.monocept.app.dto.TransactionResponseDto;
import com.monocept.app.service.BankService;
import com.monocept.app.util.PagedResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;


@RestController
@RequestMapping("/api/auth/customer")
@PreAuthorize("hasRole('USER')")
public class CustomerController {
	
	private BankService bankService;
	
	
	public CustomerController(BankService bankService) {
		super();
		this.bankService = bankService;
	}

	@PostMapping("/transactions")
	@Operation( summary = "do a transaction ")
	
	public ResponseEntity<TransactionResponseDto> doTransaction(
			@RequestParam(name = "senderAccountNumber") long senderAccountNumber,
			@RequestParam(name = "receiverAccountNumber") long receiverAccountNumber,
			@RequestParam(name = "amount") double amount) {
		return new ResponseEntity<TransactionResponseDto>(
				bankService.doTransaction(senderAccountNumber, receiverAccountNumber, amount), HttpStatus.OK);
	}

	@GetMapping("/passbook/{accountNumber}")
	@Operation( summary = "to get passbook ")
	//@PreAuthorize("hasRole('USER')")
	public ResponseEntity<PagedResponse<TransactionResponseDto>> getPassbook(
			@PathVariable(name = "accountNumber") long accountNumber,
			@RequestParam(name = "from", defaultValue = "#{T(java.time.LocalDateTime).now().minusDays(30).toString()}") String from,
			@RequestParam(name = "to", defaultValue = "#{T(java.time.LocalDateTime).now().toString()}") String to,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction) throws DocumentException, IOException, MessagingException {
		LocalDateTime fromDate = LocalDateTime.parse(from);
		LocalDateTime toDate = LocalDateTime.parse(to);
		PagedResponse<TransactionResponseDto> passbook = bankService.viewPassbook(accountNumber, fromDate, toDate, page,
				size, sortBy, direction);

		return new ResponseEntity<PagedResponse<TransactionResponseDto>>(passbook, HttpStatus.OK);
	}

	@PutMapping("/profile")
	@Operation( summary = "to update Profile")
	//@PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> updateProfile(@RequestBody ProfileRequestDto profileRequestDto) {
		String message = bankService.updateProfile(profileRequestDto);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@PutMapping("transactions/{accountNumber}/deposit")
	@Operation( summary = "to deposit amount ")
	//("hasRole('USER')")
	public ResponseEntity<AccountResponseDto> deposit(@PathVariable(name = "accountNumber") long accountNumber,
			@RequestParam(name = "amount") double amount) {
		return new ResponseEntity<AccountResponseDto>(bankService.depositAmount(accountNumber, amount),
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/accounts")
	@Operation( summary = "to get all accounts of customer ")
	//@PreAuthorize("hasRole('USER')")
	
	public ResponseEntity<PagedResponse<AccountResponseDto>> getAllAccounts(	@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "accountNumber") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction) {
		return new ResponseEntity<PagedResponse<AccountResponseDto>>(bankService.getAccounts(page,size,sortBy,direction), HttpStatus.OK);
	}
	@GetMapping("accounts/{accountNumber}/view-balance")
	@Operation( summary = "to view account balance ")
	//@PreAuthorize("hasRole('USER')")
	public ResponseEntity<AccountResponseDto> viewBalance(@PathVariable(name = "accountNumber")long accountNumber) {
		return new ResponseEntity<AccountResponseDto>(bankService.viewBalance(accountNumber),HttpStatus.OK);
	}

}
