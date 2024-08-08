package com.monocept.app.dto;

import java.util.Set;

import com.monocept.app.entity.Role;

import lombok.Data;
@Data
public class UserResponseDto {
	private Long id;

	private String email;
	private String password;
	private Set<Role> roles;
	private CustomerResponseDto customerResponseDto;
}

