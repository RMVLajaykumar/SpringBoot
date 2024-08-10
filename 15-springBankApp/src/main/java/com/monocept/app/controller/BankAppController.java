package com.monocept.app.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.monocept.app.dto.CustomerRequestDto;
import com.monocept.app.dto.CustomerResponseDto;
import com.monocept.app.dto.ProfileRequestDto;
import com.monocept.app.dto.TransactionResponseDto;
import com.monocept.app.dto.UserRequestDto;
import com.monocept.app.dto.UserResponseDto;
import com.monocept.app.service.BankService;
import com.monocept.app.util.PagedResponse;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/auth/customer")

public class BankAppController {
	BankService bankService;

	public BankAppController(BankService bankService) {
		super();
		this.bankService = bankService;
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PagedResponse<CustomerResponseDto>> getAllCustomers(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "firstName") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction) {
		PagedResponse<CustomerResponseDto> customer = bankService.getAllCustomers(page, size, sortBy, direction);
		return new ResponseEntity<PagedResponse<CustomerResponseDto>>(customer, HttpStatus.ACCEPTED);
	}

	@PostMapping("/admin/{userID}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto,
			@PathVariable(name = "userID") long userID) {
		return new ResponseEntity<UserResponseDto>(bankService.createCustomer(customerRequestDto, userID),
				HttpStatus.ACCEPTED);
	}

//	@DeleteMapping("/{id}")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<String> deleteCustomerById(@PathVariable(name = "id") long id) {
//		String message = bankService.deleteCustomerById(id);
//		return new ResponseEntity<String>(message, HttpStatus.OK);
//	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CustomerResponseDto> findCustomerbyId(@PathVariable(name = "id") long id) {
		return new ResponseEntity<CustomerResponseDto>(bankService.findCustomerByid(id), HttpStatus.OK);
	}

	@PostMapping("{cid}/account/{bid}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CustomerResponseDto> addAccount(@PathVariable(name = "cid") long cid,
			@PathVariable(name = "bid") int bid) {
		return new ResponseEntity<CustomerResponseDto>(bankService.addAccount(cid, bid), HttpStatus.OK);
	}

	@PostMapping("/transactions")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<TransactionResponseDto> doTransaction(
			@RequestParam(name = "senderAccountNumber") long senderAccountNumber,
			@RequestParam(name = "receiverAccountNumber") long receiverAccountNumber,
			@RequestParam(name = "amount") double amount) {
		return new ResponseEntity<TransactionResponseDto>(
				bankService.doTransaction(senderAccountNumber, receiverAccountNumber, amount), HttpStatus.OK);
	}

	@GetMapping("/passbook/{accountNumber}")
	@PreAuthorize("hasRole('USER')")
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
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> updateProfile(@RequestBody ProfileRequestDto profileRequestDto) {
		String message = bankService.updateProfile(profileRequestDto);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@GetMapping("/transactions")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PagedResponse<TransactionResponseDto>> getAllTransactions(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction,
			@RequestParam(name = "from", defaultValue = "#{T(java.time.LocalDateTime).now().minusDays(30).toString()}") String from,
			@RequestParam(name = "to", defaultValue = "#{T(java.time.LocalDateTime).now().toString()}") String to) {


		LocalDateTime fromDate = LocalDateTime.parse(from);
		LocalDateTime toDate = LocalDateTime.parse(to);

		PagedResponse<TransactionResponseDto> transactions = bankService.viewAllTransaction(fromDate, toDate, page,
				size, sortBy, direction);
		return new ResponseEntity<PagedResponse<TransactionResponseDto>>(transactions, HttpStatus.OK);
	}

	@PutMapping("transactions/{accountNumber}/deposit")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<AccountResponseDto> deposit(@PathVariable(name = "accountNumber") long accountNumber,
			@RequestParam(name = "amount") double amount) {
		return new ResponseEntity<AccountResponseDto>(bankService.depositAmount(accountNumber, amount),
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/accounts")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<AccountResponseDto>> getAllAccounts() {
		return new ResponseEntity<List<AccountResponseDto>>(bankService.getAccounts(), HttpStatus.OK);
	}
	
	@PutMapping("admin/customers/active/{customerID}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> activatExistingCustomer(@PathVariable(name = "customerID")long customerID) {
		return new ResponseEntity<String>( bankService.activateCustomer(customerID),HttpStatus.OK);
	}
	@DeleteMapping("admin/customers/accounts/{accountNumber}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String>  deleteAccount(@PathVariable(name = "accountNumber")long accountNumber) {
		return new ResponseEntity<String>(bankService.deleteAccount(accountNumber),HttpStatus.OK);
	}
	@PutMapping("admin/customers/accounts/active/{accountNumber}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> activateExistingAccount(@PathVariable(name = "accountNumber")long accountNumber) {
		return new ResponseEntity<String>(bankService.activateAccount(accountNumber),HttpStatus.OK);
	}
	
	@GetMapping("accounts/{accountNumber}/view-balance")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<AccountResponseDto> viewBalance(@PathVariable(name = "accountNumber")long accountNumber) {
		return new ResponseEntity<AccountResponseDto>(bankService.viewBalance(accountNumber),HttpStatus.OK);
	}
	
	@DeleteMapping("admin/customers/{customerID}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String>  deleteCustomer(@PathVariable(name = "customerID")long customerID) {
		return new ResponseEntity<String>(bankService.deleteCustomer(customerID),HttpStatus.OK);
	}
	
	

}
