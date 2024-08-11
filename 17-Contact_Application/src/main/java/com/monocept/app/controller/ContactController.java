package com.monocept.app.controller;

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

import com.monocept.app.dto.ContactDetailRequestDto;
import com.monocept.app.dto.ContactDetailResponseDto;
import com.monocept.app.dto.ContactRequestDto;
import com.monocept.app.dto.ContactResponseDto;
import com.monocept.app.dto.UserRequestDto;
import com.monocept.app.dto.UserResponseDto;
import com.monocept.app.service.ContactService;
import com.monocept.app.util.PagedResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ContactController {
	
	ContactService contactService;
	


	public ContactController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}



	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PagedResponse<UserResponseDto>> getAllUsers(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "userId") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction){
		return new ResponseEntity<PagedResponse<UserResponseDto>>(contactService.getAllUsers(page, size, sortBy, direction),HttpStatus.OK);
				
	}
	
	@PostMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
	    return new ResponseEntity<>(contactService.addUser(userRequestDto), HttpStatus.ACCEPTED);
	}

	

	@PutMapping("/users/{user_id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserResponseDto> updateUserProfile(@Valid @RequestBody UserRequestDto userRequestDto,@PathVariable(name="user_id") long user_id) {

		return new ResponseEntity<UserResponseDto>(contactService.updateUserProfile(userRequestDto,user_id), HttpStatus.ACCEPTED);
	}

	
	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable(name="id") long id){
		return new ResponseEntity<UserResponseDto>(contactService.getUserId(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteUserById(@PathVariable(name="id") long id){
		return new ResponseEntity<String>(contactService.deleteUserId(id),HttpStatus.OK);
	}
	
	
	
	@PostMapping("/contacts")
	@PreAuthorize("hasRole('STAFF')")
	public ResponseEntity<ContactResponseDto> addContact(@Valid @RequestBody ContactRequestDto contactRequestDto){
		return new ResponseEntity<ContactResponseDto>(contactService.addContact(contactRequestDto),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/contacts")
	@PreAuthorize("hasRole('STAFF')")
	public ResponseEntity<PagedResponse<ContactResponseDto>> getAllContacts(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "contactId") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction){
		 return  new ResponseEntity<PagedResponse<ContactResponseDto>>(contactService.getAllContacts(page, size, sortBy, direction),HttpStatus.OK);
	}
	
	
	@GetMapping("/contacts/{id}")
	@PreAuthorize("hasRole('STAFF')")
	public ResponseEntity<ContactResponseDto> getContactId(@PathVariable(name="id")long id){
		return new ResponseEntity<ContactResponseDto>(contactService.findContactById(id),HttpStatus.OK);
		
	}
	
	@PutMapping("/contacts/{id}")
	@PreAuthorize("hasRole('STAFF')")
	public ResponseEntity<ContactResponseDto> updateContactbyId(@PathVariable(name="id")long id,@Valid @RequestBody ContactRequestDto contactRequestDto){
		return new ResponseEntity<ContactResponseDto>(contactService.updateContactDetails(id,contactRequestDto),HttpStatus.OK);
	}
	
	@DeleteMapping("/contacts/{id}")
	@PreAuthorize("hasRole('STAFF')")
	public ResponseEntity<String> deleteContactId(@PathVariable(name="id")long id){
		return new ResponseEntity<String>(contactService.deleteContactById(id),HttpStatus.OK);
		
	}
	
	@PostMapping("contacts/{contactId}/details")
	@PreAuthorize("hasRole('STAFF')")
	public ResponseEntity<ContactDetailResponseDto> createContactDetailsforContact(@Valid @RequestBody ContactDetailRequestDto contactDetailsRequestDto,@PathVariable(name="contactId") long contactId) {
		ContactDetailResponseDto contact = contactService.createContactDetailsforContact(contactDetailsRequestDto,contactId);

		return new ResponseEntity<ContactDetailResponseDto>(contact, HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("contacts/{contactId}/details")
	@PreAuthorize("hasRole('STAFF')")
	public ResponseEntity<ContactDetailResponseDto> updateContactDetailsforContact(@Valid @RequestBody ContactDetailRequestDto contactDetailsRequestDto,@PathVariable(name="contactId") long contactId,@RequestParam(name="contactDetailsId") long contactDetailsId){
		return new ResponseEntity<ContactDetailResponseDto>(contactService.updateContactDetails(contactId, contactDetailsRequestDto,contactDetailsId),HttpStatus.OK);
	}
	
	@GetMapping("contacts/{contactId}/details")
	@PreAuthorize("hasRole('STAFF')")
	public ResponseEntity<PagedResponse<ContactDetailResponseDto>> getAllContactDetails(@RequestParam(name = "page", defaultValue = "0") int page,
	@RequestParam(name = "size", defaultValue = "5") int size,
	@RequestParam(name = "sortBy", defaultValue = "contactDetailsId") String sortBy,
	@RequestParam(name = "direction", defaultValue = "asc") String direction,
	@PathVariable(name="contactId")long contactId){
		
		return new ResponseEntity<PagedResponse<ContactDetailResponseDto>>(contactService.getAllContactDetails(page,size,sortBy,direction,contactId),HttpStatus.OK);
	}
	
	@GetMapping("contacts/{contactId}/details/{contactdetails_id}")
	@PreAuthorize("hasRole('STAFF')")
	public ResponseEntity<ContactDetailResponseDto> getContactDetailsById(@PathVariable(name="contactId")long contactId,@PathVariable(name="contactdetails_id")long contactdetails_id){
		return new ResponseEntity<ContactDetailResponseDto>(contactService.getContactDetailsById(contactId,contactdetails_id),HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("contacts/{contactId}/details/{contactdetails_id}")
	@PreAuthorize("hasRole('STAFF')")
	public ResponseEntity<String> deleteCustomerDetailsById(@PathVariable(name="contactId")long contactId,@PathVariable(name="contactdetails_id")long contactdetails_id){
		return new ResponseEntity<String>(contactService.deleteContacDetailstById(contactdetails_id,contactId),HttpStatus.OK);
	}
	
	
	
	
	
	

}
