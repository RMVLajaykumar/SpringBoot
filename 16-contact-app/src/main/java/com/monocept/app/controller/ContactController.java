package com.monocept.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.app.dto.UserResponseDto;
import com.monocept.app.service.ContactService;

@RestController
@RequestMapping("/api/admin")
public class ContactController {
	
	ContactService contactService;
	
	
	public ContactController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}


	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserResponseDto>> getAllUsers(){
		return new ResponseEntity<List<UserResponseDto>>(contactService.getAllUsers(),HttpStatus.OK);
		
		
		
	}
	
	

}
