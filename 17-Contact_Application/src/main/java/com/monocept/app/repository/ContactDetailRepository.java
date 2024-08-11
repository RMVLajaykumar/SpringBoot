package com.monocept.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.app.entity.Contact;
import com.monocept.app.entity.ContactDetails;

public interface ContactDetailRepository extends JpaRepository<ContactDetails, Long> {

	Page<ContactDetails> findByContact(Contact contact, PageRequest pageRequest);

}
