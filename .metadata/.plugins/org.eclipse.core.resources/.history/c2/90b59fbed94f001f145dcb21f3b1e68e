package com.monocept.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monocept.app.dto.EmployeeDto;
import com.monocept.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	
	
	@Query("select e from Employee e where e.name=?1")
	public Employee findByName(String name);
	@Query("select e from Employee e where e.email=?1")
	public Employee findByEmail(String email);
	@Query("select e from Employee e where e.active=true")
	public Page<Employee> findByActiveTrue(Pageable pageable);
	@Query("select e from Employee e where e.name like :name%")
	public  Page<Employee> findByNameStartingWith(@Param("name")String Startswith,Pageable pageable);
	@Query("select count(e) from Employee e where e.active=true")
	public Integer countByActiveTrue();
	@Query("select e from Employee e where e.salary>=?1 and designation=?2")
	public Page<Employee> findBySalaryGreaterThanAndDesignation(double salary,String designation, Pageable pageable);
	@Query("select count(e) from Employee e where e.designation=?1")
	public int countByDesignation(String designation);
	@Query("select e from Employee e where e.salary between ?1 and ?2")
	public Page<Employee> findBySalaryBetween(double startSalary,double endSalary, Pageable pageable);
	@Query("select count(e) from Employee e where e.salary>=?1 and active=true")
	public int countBySalaryGreaterThanandActive(double salary);
	
	
}
