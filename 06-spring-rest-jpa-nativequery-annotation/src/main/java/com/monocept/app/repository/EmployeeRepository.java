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
	
	
	@Query(value="select * from employee where name=?1",nativeQuery=true)
	public Employee findByName(String name);
	@Query(value="select * from employee where email=?1",nativeQuery=true)
	public Employee findByEmail(String email);
	@Query(value="select * from employee  where active=true",nativeQuery=true)
	public Page<Employee> findByActiveTrue(Pageable pageable);
	@Query(value="select * from employee where name like :name%",nativeQuery=true)
	public  Page<Employee> findByNameStartingWith(@Param("name")String Startswith,Pageable pageable);
	@Query(value="select count(*) from employee e where e.active=true",nativeQuery=true)
	public Integer countByActiveTrue();
	@Query(value="select * from employee e where e.salary>=?1 and designation=?2",nativeQuery=true)
	public Page<Employee> findBySalaryGreaterThanAndDesignation(double salary,String designation, Pageable pageable);
	@Query(value="select count(*) from employee e where e.designation=?1",nativeQuery=true)
	public int countByDesignation(String designation);
	@Query(value="select * from employee e where e.salary between ?1 and ?2",nativeQuery=true)
	public Page<Employee> findBySalaryBetween(double startSalary,double endSalary, Pageable pageable);
	@Query(value="select count(*) from employee e where e.salary>=?1 and active=true",nativeQuery=true)
	public int countBySalaryGreaterThanandActive(double salary);
	
	
}
