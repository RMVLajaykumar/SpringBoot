package com.monocept.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
