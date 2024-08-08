package com.monocept.app.dto;

import java.util.Set;

import com.monocept.app.entity.Customer;
import com.monocept.app.entity.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;



@Data
public class UserRequestDto {


   
    private Long id;

   
    private String email;
   
    private String password;

   
    private Set<Role> roles;
    
   
    private Customer customer;

	
    
}
