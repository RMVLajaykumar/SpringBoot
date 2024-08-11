package com.monocept.app.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.monocept.app.entity.ContactDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class ContactResponseDto {
	
		private long contactId;
		private String firstName;
		
		private String lastName;
		
		private boolean active;
		
	
		private List<ContactDetails> contactDetails;
		
		private UserResponseDto user;
	}


