package com.monocept.app.dto;

import java.util.List;

import com.monocept.app.entity.ContactDetails;
import com.monocept.app.entity.User;

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
public class ContactRequestDto {
	
	private long contactId;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotNull
	private boolean active;
	
	private List<ContactDetails> contactDetails;
	
	private UserRequestDto user;
}

