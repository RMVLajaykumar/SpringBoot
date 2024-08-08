package com.monocept.hibernate.Dao;

import java.util.List;

import com.monocept.hibernate.entity.Employee;

public interface EmployeeDao {

	

	void addEmployee(Employee employee);

	List<Employee> getAllEmployee();

	List<Employee> getNames(String string);

	Employee getEmployeebyId(int i);

	List<Employee> getEmployeeByName(String string);

	void updateEmployee(Employee employee);

	void updateEmployee2(Employee employee);

	void deleteEmployee(int i);

	void deleteEmployee2(int i);
	
	
	

}
