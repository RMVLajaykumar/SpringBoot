package com.monocept.app.service;

import java.util.List;

import com.monocept.app.dto.ContactDetailRequestDto;
import com.monocept.app.dto.ContactDetailResponseDto;
import com.monocept.app.dto.ContactRequestDto;
import com.monocept.app.dto.ContactResponseDto;
import com.monocept.app.dto.UserRequestDto;
import com.monocept.app.dto.UserResponseDto;
import com.monocept.app.util.PagedResponse;

public interface ContactService {

	PagedResponse<UserResponseDto> getAllUsers(int page, int size, String sortBy, String direction);

	UserResponseDto addUser(UserRequestDto userRequestDto);

	UserResponseDto updateUserProfile(UserRequestDto userRequestDto, long user_id);

	UserResponseDto getUserId(long id);

	String deleteUserId(long id);

	ContactResponseDto addContact(ContactRequestDto contactRequestDto);

	PagedResponse<ContactResponseDto> getAllContacts(int page, int size, String sortBy, String direction);

	ContactResponseDto findContactById(long id);

	ContactResponseDto updateContactDetails(long id, ContactRequestDto contactRequestDto);

	String deleteContactById(long id);

	ContactDetailResponseDto createContactDetailsforContact(ContactDetailRequestDto contactDetailsRequestDto,
			long contactId);

	ContactDetailResponseDto updateContactDetails(long contactId, ContactDetailRequestDto contactDetailsRequestDto, long contactDetailsId);

	PagedResponse<ContactDetailResponseDto> getAllContactDetails(int page, int size, String sortBy, String direction,
			long contactId);

	ContactDetailResponseDto getContactDetailsById(long contactId, long contactdetails_id);

	String deleteContacDetailstById(long contactdetails_id, long contactId);

}
