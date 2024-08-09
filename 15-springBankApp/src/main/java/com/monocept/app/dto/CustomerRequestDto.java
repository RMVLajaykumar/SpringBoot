package com.monocept.app.dto;

import java.util.List;

import com.monocept.app.entity.Account;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class CustomerRequestDto {
	
	private long customer_id;
	
	@NotBlank(message="first name cannot be blank")
	@Size(min = 2 , max = 50)
	private String firstName;
	
	@NotBlank(message="last name cannot be blank")
	@Size(min = 2,max = 50)
	private String lastName;
	
	@NotNull
	private double totalBalance;
	
	private List<AccountRequestDto> accounts;
	

}