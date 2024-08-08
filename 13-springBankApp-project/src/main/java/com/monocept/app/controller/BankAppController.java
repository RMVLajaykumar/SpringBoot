package com.monocept.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.app.dto.CustomerRequestDto;
import com.monocept.app.dto.CustomerResponseDto;
import com.monocept.app.dto.TransactionResponseDto;
import com.monocept.app.service.BankService;
import com.monocept.app.util.PagedResponse;

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
	public ResponseEntity<PagedResponse<CustomerResponseDto>> getAllCustomers(@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="sortBy",defaultValue = "firstName")String sortBy,
			@RequestParam(name="direction",defaultValue="asc")String direction)
	{
		PagedResponse <CustomerResponseDto> customer=bankService.getAllCustomers(page,size,sortBy,direction);
		return new ResponseEntity<PagedResponse<CustomerResponseDto>>(customer,HttpStatus.ACCEPTED);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity <CustomerResponseDto> addCustomer(@RequestBody CustomerRequestDto customerrequestdto) {
	 return new ResponseEntity<CustomerResponseDto>(bankService.addCustomer(customerrequestdto),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public  ResponseEntity<String>deleteCustomerById(@PathVariable(name="id") long id){
		String message=bankService.deleteCustomerById(id);
		  return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	 public ResponseEntity <CustomerResponseDto> findCustomerbyId(@PathVariable(name="id") long id){
		return new ResponseEntity <CustomerResponseDto>(bankService.findCustomerByid(id),HttpStatus.OK);
	}
	
	
	@PostMapping("{cid}/account/{bid}")
	public ResponseEntity<CustomerResponseDto> addAccount(@PathVariable(name="cid")long cid ,@PathVariable(name="bid") int bid){
		return new ResponseEntity<CustomerResponseDto>(bankService.addAccount(cid,bid),HttpStatus.OK);
	}
	
	
	@PostMapping("{senderAccountno}/transaction/{receiverAccountno}/{amount}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<TransactionResponseDto> doTransaction(@PathVariable(name="senderAccountno") long senderAccountno,@PathVariable(name="receiverAccountno") long receiverAccountno,@PathVariable(name="amount") double amount){
		return new ResponseEntity<TransactionResponseDto>(bankService.doTransaction(senderAccountno,receiverAccountno,amount),HttpStatus.OK);
	}
	
	
	@GetMapping("/transaction")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PagedResponse<TransactionResponseDto>> viewTransaction(@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="sortBy",defaultValue = "id")String sortBy,
			@RequestParam(name="direction",defaultValue="asc")String direction){
		return new  ResponseEntity<PagedResponse<TransactionResponseDto>>(bankService.viewAllTransaction(page,size,sortBy,direction),HttpStatus.OK);
	}
	
	@GetMapping("/passbook/{Accountno}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<List<TransactionResponseDto>> viewPassbook(@PathVariable(name="Accountno")long accountNo){
		return new ResponseEntity<List<TransactionResponseDto>>(bankService.viewPassbook(accountNo),HttpStatus.OK);
	} 
	
//	@GetMapping("/searchByDate")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<List<TransactionResponseDto>> searchByDate(@RequestParam(name="fromDate") LocalDateTime fromDate, @RequestParam(name="toDate") LocalDateTime toDate){
//		return new ResponseEntity<List<TransactionResponseDto>>(bankService.searchByDate(fromDate,toDate),HttpStatus.OK);
//	}
//	
	
	
	
}
