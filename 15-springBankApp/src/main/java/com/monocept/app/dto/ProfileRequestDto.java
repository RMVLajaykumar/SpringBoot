package com.monocept.app.dto;



import lombok.Data;

@Data
public class ProfileRequestDto {
	private String firstName;
	private String lastName;
	private String Email;
	private String password;
}
