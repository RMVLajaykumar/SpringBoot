package com.monocept.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.monocept.app.dto.UserResponseDto;
import com.monocept.app.entity.User;
import com.monocept.app.repository.UserRepository;

import lombok.Data;


@Service
public class contactServiceImpl implements ContactService{
	
	UserRepository userRepository;
	

	public contactServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public List<UserResponseDto> getAllUsers() {
	  List<User> user=userRepository.findAll();
	  return covertUserToUserResponseDto(user);
		
	}

	private List<UserResponseDto> covertUserToUserResponseDto(List<User> user) {
		List<UserResponseDto> users=new ArrayList<>();
		
		for(User userDto:user) {
			UserResponseDto userResponseDto = new UserResponseDto();
			userResponseDto.setEmail(userDto.getEmail());
			userResponseDto.setFirstName(userDto.getFirstName());
			userResponseDto.setLastName(userDto.getLastName());
			userResponseDto.setActive(userDto.isActive());
			userResponseDto.setAdmin(userDto.isAdmin());
			userResponseDto.setUserId(userDto.getUserId());
			users.add(userResponseDto);
		}
		return users;
		
	
	
	}

}
