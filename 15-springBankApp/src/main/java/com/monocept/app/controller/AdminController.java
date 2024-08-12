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

import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/auth/admin")

public class AdminController {
	BankService bankService;

	public AdminController(BankService bankService) {
		super();
		this.bankService = bankService;
	}

	@GetMapping
	@Operation( summary = "get all customers ")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PagedResponse<CustomerResponseDto>> getAllCustomers(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "firstName") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction) {
		PagedResponse<CustomerResponseDto> customer = bankService.getAllCustomers(page, size, sortBy, direction);
		return new ResponseEntity<PagedResponse<CustomerResponseDto>>(customer, HttpStatus.ACCEPTED);
	}

	@PostMapping("{userID}")
	@Operation(
		      summary = "create customer by id")
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
	@Operation(
		      summary = "find customer by id",
		      description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.")
		     // tags = {  "get" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CustomerResponseDto> findCustomerbyId(@PathVariable(name = "id") long id) {
		return new ResponseEntity<CustomerResponseDto>(bankService.findCustomerByid(id), HttpStatus.OK);
	}

	@PostMapping("{cid}/account/{bid}")
	@Operation( summary = "add account to customer")	     
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CustomerResponseDto> addAccount(@PathVariable(name = "cid") long cid,
			@PathVariable(name = "bid") int bid) {
		return new ResponseEntity<CustomerResponseDto>(bankService.addAccount(cid, bid), HttpStatus.OK);
	}

	

	@GetMapping("/transactions")
	@Operation( summary = "to get all transactions")
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

	
	
	@PutMapping("customers/active/{customerID}")
	@Operation( summary = "activating existing customer ")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> activatingExistingCustomer(@PathVariable(name = "customerID")long customerID) {
		return new ResponseEntity<String>( bankService.activateCustomer(customerID),HttpStatus.OK);
	}
	@DeleteMapping("customers/accounts/{accountNumber}")
	@Operation( summary = "deactivating existing Account ")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String>  deactivateAccount(@PathVariable(name = "accountNumber")long accountNumber) {
		return new ResponseEntity<String>(bankService.deleteAccount(accountNumber),HttpStatus.OK);
	}
	@PutMapping("customers/accounts/active/{accountNumber}")
	@Operation( summary = "activating existing customer's account")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> activatingExistingAccount(@PathVariable(name = "accountNumber")long accountNumber) {
		return new ResponseEntity<String>(bankService.activateAccount(accountNumber),HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("customers/{customerID}")
	@Operation( summary = "deactivating existing Customer ")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String>  deactiveCustomer(@PathVariable(name = "customerID")long customerID) {
		return new ResponseEntity<String>(bankService.deleteCustomer(customerID),HttpStatus.OK);
	}
	
	

}
