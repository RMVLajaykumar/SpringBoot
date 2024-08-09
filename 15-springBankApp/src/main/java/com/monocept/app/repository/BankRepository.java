package com.monocept.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.app.entity.Bank;

public interface BankRepository extends JpaRepository<Bank,Integer> {

}
