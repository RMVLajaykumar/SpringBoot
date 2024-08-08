package com.monocept.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.app.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
