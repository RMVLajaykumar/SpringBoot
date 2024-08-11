package com.monocept.app.dto;

import com.monocept.app.entity.ContactType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class ContactDetailRequestDto {
	
	
	private long contactDetailsId;
	 private ContactRequestDto contact;
	 @NotNull
	   private ContactType contactType;
	 
     
	

}
