package com.monocept.app.service;

import java.util.List;

import com.monocept.app.entity.Employee;

public interface EmployeeService {
	
	public Employee saveEmployee(Employee employee);
	public List<Employee> findAll();
	public Employee findById(int id);
	public void deleteById(int id);
	public Employee findEmployeeAddresswithAddress(int id);

}
