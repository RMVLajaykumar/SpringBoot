package com.monocept.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class ContactDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long contactDetailsId;
	@NotNull
	private ContactType contactType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id")
	private Contact contact;
}