package com.monocept.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.app.dto.EmployeeDto;
import com.monocept.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	
	public Employee findByName(String name);
	
	public Employee findByEmail(String email);
	
	public Page<Employee> findByActiveTrue(Pageable pageable);
	
	public  Page<Employee> findByNameStartingWith(String Startswith,Pageable pageable);
	
	public Integer countByActiveTrue();
	
	public Page<Employee> findBySalaryGreaterThanAndDesignation(double salary,String designation, Pageable pageable);
	
	public int countByDesignation(String designation);
	
	public Page<Employee> findBySalaryBetween(double startSalary,double endSalary, Pageable pageable);
	public int countBySalaryGreaterThan(double salary);
	
	
}
