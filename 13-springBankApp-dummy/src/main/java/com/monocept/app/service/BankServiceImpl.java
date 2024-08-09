package com.monocept.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.monocept.app.dto.AccountRequestDto;
import com.monocept.app.dto.AccountResponseDto;
import com.monocept.app.dto.BankRequestDto;
import com.monocept.app.dto.CustomerRequestDto;
import com.monocept.app.dto.CustomerResponseDto;
import com.monocept.app.dto.ProfileRequestDto;
import com.monocept.app.dto.TransactionResponseDto;
import com.monocept.app.dto.UserRequestDto;
import com.monocept.app.dto.UserResponseDto;
import com.monocept.app.entity.Account;
import com.monocept.app.entity.Bank;
import com.monocept.app.entity.Customer;
import com.monocept.app.entity.Transaction;
import com.monocept.app.entity.TransactionType;
import com.monocept.app.entity.User;
import com.monocept.app.exception.CustomerNotFoundException;
import com.monocept.app.exception.NoRecordFoundException;
import com.monocept.app.repository.AccountRepository;
import com.monocept.app.repository.BankRepository;
import com.monocept.app.repository.CustomerRepository;
import com.monocept.app.repository.TransactionRepository;
import com.monocept.app.repository.UserRepository;
import com.monocept.app.util.PagedResponse;

@Service
public class BankServiceImpl implements BankService{
	CustomerRepository customerRepository;
	
	AccountRepository accountRepository;
	BankRepository bankRepository;
	TransactionRepository transactionRepository;
	UserRepository userRepository;
	 PasswordEncoder passwordEncoder;

	


	

	


	public BankServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository,
			BankRepository bankRepository, TransactionRepository transactionRepository, UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		super();
		this.customerRepository = customerRepository;
		this.accountRepository = accountRepository;
		this.bankRepository = bankRepository;
		this.transactionRepository = transactionRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public PagedResponse<CustomerResponseDto> getAllCustomers(int page, int size, String sortBy, String direction) {
		Sort sort = Sort.by(sortBy);
        if (direction.equalsIgnoreCase(Sort.Direction.DESC.name())) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        
        
		  List<CustomerResponseDto> customerDto = convertCustomertoCustomerDto(customerPage.getContent());
		  return new PagedResponse<>(customerDto, customerPage.getNumber(), customerPage.getSize(),
				  customerPage.getTotalElements(), customerPage.getTotalPages(), customerPage.isLast());
	}


	private List<CustomerResponseDto> convertCustomertoCustomerDto(List<Customer> all) {
		List<CustomerResponseDto> customers=new ArrayList<>();
		
		for(Customer i:all) {
			CustomerResponseDto customer=new CustomerResponseDto();
			customer.setCustomer_id(i.getCustomer_id());
			customer.setFirstName(i.getFirstName());
			customer.setLastName(i.getLastName());
			customer.setTotalBalance(i.getTotalBalance());		
			customer.setAccounts(convertAccounttoAccountResponseDto(i.getAccounts()));
			customers.add(customer);
		}
		return customers;
	}

  

	private List<AccountResponseDto> convertAccounttoAccountResponseDto(List<Account> accounts) {
	
	
		List<AccountResponseDto> accountResponseDto=new ArrayList<>();
		for(Account account:accounts) {
			
			accountResponseDto.add(	convertAccounttoAccountResponseDto(account));
			
		}
		return accountResponseDto;
	}


	private AccountResponseDto convertAccounttoAccountResponseDto(Account account) {
		
		AccountResponseDto accountResponseDto= new AccountResponseDto();
		accountResponseDto.setAccountNumber(account.getAccountNumber());
		accountResponseDto.setBalance(account.getBalance());
		return accountResponseDto;
	}


//	@Override
//	public CustomerResponseDto addCustomer(CustomerRequestDto customerrequestdto) {
//		
//		Customer customer=convertcustomerRequestDtoToCustomer(customerrequestdto);
//		return convertCustomerTocustomerResponseDto(customerRepository.save(customer));
//		
//	}
//
//
//	private CustomerResponseDto convertCustomerTocustomerResponseDto(Customer customer) {
//		CustomerResponseDto customer1 =  new CustomerResponseDto();
//		customer1.setCustomer_id(customer.getCustomer_id());
//		customer1.setFirstName(customer.getFirstName());
//		customer1.setLastName(customer.getLastName());
//		customer1.setTotalBalance(customer.getTotalBalance());
//		return customer1;
//	}
//
//
//	private Customer convertcustomerRequestDtoToCustomer(CustomerRequestDto customerrequestdto) {
//		Customer customer =  new Customer();
//		customer.setCustomer_id(customerrequestdto.getCustomer_id());
//		customer.setFirstName(customerrequestdto.getFirstName());
//		customer.setLastName(customerrequestdto.getLastName());
//		customer.setTotalBalance(customerrequestdto.getTotalBalance());
//		return customer;
//	}


	@Override
	public String deleteCustomerById(long id) {
		
		
		Customer customer=customerRepository.findById(id).orElse(null);
		if(customer!=null) {
			customerRepository.deleteById(id);
			
		}
		else {
			throw new CustomerNotFoundException("customer not found with id: "+id);
		}
		return "customer deleted Successfully";
		
	}


	@Override
	public CustomerResponseDto findCustomerByid(long id) {
		Customer customer = customerRepository.findById(id).orElse(null);
		if(customer==null) {
			throw new CustomerNotFoundException("customer not found with id: "+id);
		}
		else {
		return convertCustomerToCustomerResponseDto(customer);
		}
	}


	@Override
	public CustomerResponseDto addAccount(long cid,int bid) {
		
		 Account account = new Account();
		 Bank bank = bankRepository.findById(bid).orElse(null);
		
		 
		 if(bank != null) {
			 Customer customer = customerRepository.findById(cid).orElse(null);
			 
			 if(customer != null){
				 
				//return convertAccounttoAccountResponseDto(accountRepository.save(account));
				 account.setBalance(1000);
				 account.setBank(bank);
				 account.setCustomer(customer);
				 
				 customer.addbankAccount(account);
				 double total_salary=1000;
				 if(accountRepository.getTotalBalance(customer)!=0) {
					 total_salary+=accountRepository.getTotalBalance(customer);
				 }
				 customer.setTotalBalance(total_salary);
				 Customer save = customerRepository.save(customer);
				 return convertCustomerToCustomerResponseDto(save);
				 
			 }
		 }
		return null;
		
		 
	
	
	}


	private Account convertAcountResponseDtoToAccount(AccountRequestDto accountRequestDto) {
		Account account=new Account();
		account.setAccountNumber(accountRequestDto.getAccountNumber());
		account.setBalance(accountRequestDto.getBalance());
		account.setBank(convertBankDtotoBank(accountRequestDto.getBankrequestDto()));
		account.setCustomer(convertcustomerDtoToCustomer(accountRequestDto.getCustomerRequestDto()));
		return account;
		
	}


	


	private Customer convertcustomerDtoToCustomer(CustomerRequestDto customerRequestDto) {
	
		Customer customer = new Customer();
		customer.setCustomer_id(customerRequestDto.getCustomer_id());
		customer.setFirstName(customerRequestDto.getFirstName());
		customer.setLastName(customerRequestDto.getLastName());
		customer.setTotalBalance(customerRequestDto.getTotalBalance());
		return customer;
	}


	private Bank convertBankDtotoBank(BankRequestDto bank) {
		Bank bank1 = new Bank();
		bank1.setBank_id(bank.getBank_id());
		bank1.setAbbreviation(bank.getAbbreviation());
		bank1.setFullName(bank.getFullName());
		return bank1;
	}


//	@Override
//	public TransactionResponseDto doTransaction(long senderAccountno, long receiverAccountno, double amount) {
//		
//		
//		Account senderAccount=accountRepository.findById(senderAccountno).orElse(null);
//		Account receiverAccount=accountRepository.findById(receiverAccountno).orElse(null);
//		
//		if(senderAccount==null){
//			throw new NoRecordFoundException("sender account number no found");
//		}
//		if(receiverAccount==null) {
//			throw new NoRecordFoundException("recevier account number no found");
//		}
//		if(senderAccount==receiverAccount) {
//			throw new NoRecordFoundException("both Account numbers are same");
//		}
//		if(senderAccount.getBalance()<amount) {
//			throw new NoRecordFoundException("Insufficient Balance");
//		}
//		
//	
//			senderAccount.setBalance(senderAccount.getBalance()-amount);
//			receiverAccount.setBalance(receiverAccount.getBalance()+amount);
//			Customer customer = new Customer();
//
//			Customer sender=senderAccount.getCustomer();
//			sender.setTotalBalance(sender.getTotalBalance()-amount);
//			
//			Customer receiver=receiverAccount.getCustomer();
//			receiver.setTotalBalance(receiver.getTotalBalance()+amount);
//			
//			customerRepository.save(sender);
//			customerRepository.save(receiver);
//			
//			
			
//			accountRepository.save(senderAccount);
//			accountRepository.save(receiverAccount);
//			
//		Transaction transaction=new Transaction();
//		transaction.setAmount(amount);
//		transaction.setSenderAccount(senderAccount);
//		transaction.setReceiverAccount(receiverAccount);
//		transaction.setTransactionType(TransactionType.Transfer);
//	
//	    Transaction save = transactionRepository.save(transaction);
//	 
//	    
//		return  convertTransactiontoTransactionDto(save);
//			
//		
//	}
	@Override
	public TransactionResponseDto doTransaction(long senderAccountNumber, long receiverAccountNumber,
			double amount) {
		Optional<User> user = userRepository.findByEmail(getEmailFromSecurityContext());
		List<Account> accounts = user.get().getCustomer().getAccounts();
		for (Account account : accounts) {
			if (account.getAccountNumber() == senderAccountNumber) {
				Account senderAccount = accountRepository.findById(senderAccountNumber).orElse(null);
				Account receiverAccount = accountRepository.findById(receiverAccountNumber).orElse(null);
				if (senderAccount == null || receiverAccount == null) {
					throw new NoRecordFoundException("Please check the sender account number " + senderAccountNumber
							+ " and receiver account number " + receiverAccountNumber);
				}
				if (senderAccount.equals(receiverAccount)) {
					throw new NoRecordFoundException("self transfer to the same account number not possible");
				}
				if (senderAccount.getBalance() < amount) {
					throw new NoRecordFoundException("Insufficient Funds please check the balance again");
				}
				senderAccount.setBalance(senderAccount.getBalance() - amount);
				receiverAccount.setBalance(receiverAccount.getBalance() + amount);
				accountRepository.save(senderAccount);
				accountRepository.save(receiverAccount);
				Customer senderCustomer = senderAccount.getCustomer();
				Customer receiverCustomer = receiverAccount.getCustomer();
				senderCustomer.setTotalBalance(senderCustomer.getTotalBalance() - amount);
				receiverCustomer.setTotalBalance(receiverCustomer.getTotalBalance() + amount);

				customerRepository.save(senderCustomer);
				customerRepository.save(receiverCustomer);
				Transaction transaction = new Transaction();
				transaction.setAmount(amount);
				transaction.setSenderAccount(senderAccount);
				transaction.setReceiverAccount(receiverAccount);
				transaction.setTransactionType(TransactionType.Transfer);
				return  convertTransactiontoTransactionDto(transactionRepository.save(transaction));
			}
		}
		throw new NoRecordFoundException("Your account number is wrong");

	}


	private TransactionResponseDto convertTransactiontoTransactionDto(Transaction save) {
		
		TransactionResponseDto transactionResponseDto=new TransactionResponseDto();
		transactionResponseDto.setAmount(save.getAmount());
		transactionResponseDto.setId(save.getId());
		transactionResponseDto.setSenderAccount(convertAccounttoAccountResponseDto(save.getSenderAccount()));
		transactionResponseDto.setReceiverAccount(convertAccounttoAccountResponseDto(save.getReceiverAccount()));
		transactionResponseDto.setTransactionDate(save.getTransactionDate());
		transactionResponseDto.setTransactionType(save.getTransactionType());
		
		return transactionResponseDto;
		
	}


	


	private List<TransactionResponseDto> convertTransactiontoTransactionDto(List<Transaction> all) {
		
		List<TransactionResponseDto> transactionResponseDto=new ArrayList<>();
		for(Transaction t:all) {
			transactionResponseDto.add(convertTransactiontoTransactionDto(t));
			
		}
		return transactionResponseDto;
	}


	@Override
	public PagedResponse<TransactionResponseDto> viewAllTransaction(LocalDateTime fromDate, LocalDateTime toDate,
			int page, int size, String sortBy, String direction) {
		Sort sort = Sort.by(sortBy);
		if (direction.equalsIgnoreCase("desc")) {
			sort = sort.descending();
		} else {
			sort = sort.ascending();
		}
//		fromDate = fromDate.truncatedTo(ChronoUnit.SECONDS);
//		toDate = toDate.truncatedTo(ChronoUnit.SECONDS);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		System.out.println("Page request: " + pageRequest);
		Page<Transaction> pagedResponse = transactionRepository.findAllByTransactionDateBetween(fromDate, toDate,
				pageRequest);
		System.out.println(
				"Fetched transactions: " + convertTransactiontoTransactionDto(pagedResponse.getContent()));
		PagedResponse<TransactionResponseDto> response = new PagedResponse<>(
				convertTransactiontoTransactionDto(pagedResponse.getContent()), pagedResponse.getNumber(),
				pagedResponse.getSize(), pagedResponse.getTotalElements(), pagedResponse.getTotalPages(),
				pagedResponse.isLast());
		return response;
	}


	


	private List<TransactionResponseDto> covertPassbooktopassbookDto(List<Transaction> viewPassbook,long accountNo) {
		List<TransactionResponseDto> transactionResponseDtos=new ArrayList<>();
		for(Transaction t :viewPassbook) {
			transactionResponseDtos.add(covertPassbooktopassbookDto(t,accountNo));
		}
		return transactionResponseDtos;
	}


	private TransactionResponseDto covertPassbooktopassbookDto(Transaction t,long accountNo) {
		 TransactionResponseDto transactionDto=new TransactionResponseDto();
		 
		 if((t.getSenderAccount().getAccountNumber())==accountNo) {
			 transactionDto.setTransactionType(TransactionType.Debit);
			 
		 }
		 else {
			 transactionDto.setTransactionType(TransactionType.Credit);
		 }
		 
		 transactionDto.setAmount(t.getAmount());
		 transactionDto.setId(t.getId());
		 transactionDto.setReceiverAccount(convertAccounttoAccountResponseDto(t.getReceiverAccount()));
		 transactionDto.setSenderAccount(convertAccounttoAccountResponseDto(t.getSenderAccount()));
		 transactionDto.setTransactionDate(t.getTransactionDate());
		return transactionDto;
	}


	

	@Override
	public UserResponseDto createCustomer(CustomerRequestDto customerRequestDto,long userID) {
		User user = userRepository.findById(userID).orElse(null);
		if(user==null) {
			throw new NoRecordFoundException("User not found with the following id "+userID);
		}
		if(user.getCustomer()!=null) {
			throw new NoRecordFoundException("Customer already exists");
		}
		Customer customer = convertCustomerRequestToCustomer(customerRequestDto);
		user.setCustomer(customer);
		return convertUserToUserDto(userRepository.save(user));
	}

	private UserResponseDto convertUserToUserDto(User save) {
		UserResponseDto responseDto=new UserResponseDto();
		responseDto.setId(save.getId());
		responseDto.setRoles(save.getRoles());
		responseDto.setCustomerResponseDto(convertCustomerToCustomerResponseDto(save.getCustomer()));
		responseDto.setEmail(save.getEmail());
		responseDto.setPassword(save.getPassword());
		return responseDto;
	}

	private CustomerResponseDto convertCustomerToCustomerResponseDto(Customer save) {
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setCustomer_id(save.getCustomer_id());
		customerResponseDto.setFirstName(save.getFirstName());
		customerResponseDto.setLastName(save.getLastName());
		customerResponseDto.setTotalBalance(save.getTotalBalance());
		return customerResponseDto;
	}

	private Customer convertCustomerRequestToCustomer(CustomerRequestDto customerRequestDto) {
		Customer customer = new Customer();
		customer.setFirstName(customerRequestDto.getFirstName());
		customer.setLastName(customerRequestDto.getLastName());
		customer.setTotalBalance(customerRequestDto.getTotalBalance());	
		return customer;
	}


	@Override
	public String updateProfile(ProfileRequestDto profileRequestDto) {
		User user = userRepository.findByEmail(getEmailFromSecurityContext()).orElse(null);
		if(user.getCustomer()==null) {
			throw new NoRecordFoundException("Cannot update the customer details still you havn't have customer id");
		}
		Customer customer = user.getCustomer();
		if(profileRequestDto.getEmail()!=null && !profileRequestDto.getEmail().isEmpty() && profileRequestDto.getEmail().length()!=0) {
			user.setEmail(profileRequestDto.getEmail());  
		}
		if(profileRequestDto.getFirstName()!=null && !profileRequestDto.getFirstName().isEmpty() && profileRequestDto.getFirstName().length()!=0) {
			customer.setFirstName(profileRequestDto.getFirstName());
		}
		if(profileRequestDto.getLastName()!=null && !profileRequestDto.getLastName().isEmpty() && profileRequestDto.getLastName().length()!=0) {
			customer.setLastName(profileRequestDto.getLastName());
		}
		if(profileRequestDto.getPassword()!=null && !profileRequestDto.getPassword().isEmpty() && profileRequestDto.getPassword().length()!=0) {
			user.setPassword(passwordEncoder.encode(profileRequestDto.getPassword()));
		}

		userRepository.save(user);
		
		return "user succesfully updated";
	}

	private String getEmailFromSecurityContext() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			return userDetails.getUsername();
		}
		return null;
	}


	@Override
	public PagedResponse<TransactionResponseDto> viewPassbook(long accountNumber, LocalDateTime from, LocalDateTime to, int page,
			int size, String sortBy, String direction) {
		Sort sort = Sort.by(sortBy);
		if (direction.equalsIgnoreCase(Sort.Direction.DESC.name())) {
			sort.descending();
		} else {
			sort.ascending();
		}

		String email = getEmailFromSecurityContext();
		Optional<User> user = userRepository.findByEmail(email);
		if(user.get().getCustomer()==null) {
			throw new NoRecordFoundException("still you havn't have customer id");
		}
		List<Account> accounts = user.get().getCustomer().getAccounts();
		for (Account acc : accounts) {
			if (acc.getAccountNumber() == accountNumber) {
				Account account = accountRepository.findById(accountNumber).orElse(null);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
				Page<Transaction> pagedResponse = transactionRepository.viewPassbook(account,from,to,pageRequest);

				return new PagedResponse<TransactionResponseDto>(
						convertTransactiontoTransactionDto(pagedResponse.getContent(), accountNumber),
						pagedResponse.getNumber(), pagedResponse.getSize(), pagedResponse.getTotalElements(),
						pagedResponse.getTotalPages(), pagedResponse.isLast());
			}
		}
		throw new NoRecordFoundException("Please give valid account number");
		

	}


	private List<TransactionResponseDto> convertTransactiontoTransactionDto(List<Transaction> passbook,
			long accountNumber) {
		List<TransactionResponseDto> list = new ArrayList<>();
		for (Transaction transaction : passbook) {
			TransactionResponseDto responseDto = new TransactionResponseDto();
			responseDto.setAmount(transaction.getAmount());
			responseDto.setSenderAccount(convertAccounttoAccountResponseDto(transaction.getSenderAccount()));
			responseDto.setReceiverAccount(convertAccounttoAccountResponseDto(transaction.getReceiverAccount()));
			responseDto.setId(transaction.getId());
			responseDto.setTransactionDate(transaction.getTransactionDate());
			if (transaction.getSenderAccount().getAccountNumber() == accountNumber) {
				responseDto.setTransactionType(TransactionType.Debit);
			} else {
				responseDto.setTransactionType(TransactionType.Credit);
			}
			list.add(responseDto);
		}
		return list;
	}


	

	


	

	


	
}
