package com.monocept.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.app.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {

}
