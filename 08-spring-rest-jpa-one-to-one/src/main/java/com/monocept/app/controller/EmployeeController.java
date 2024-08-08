package com.monocept.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.monocept.app.entity.Employee;
import com.monocept.app.service.EmployeeService;

@RestController
@RequestMapping("/onetoone")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	
	@GetMapping("employees")
	public List<Employee> getAllEmployees() {
		return employeeService.findAll();
	}
	
	@PostMapping("add-employeee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}
	
	
	@GetMapping("employees/{id}")
	public Employee getEmployeeById(@PathVariable(name="id") int id) {
		return employeeService.findById(id);
	}
	
	

	@PutMapping("update-employee")
	public Employee UpdateEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}
	
	@DeleteMapping("employees/{id}")
	public void deleteEmployeeById(@PathVariable(name="id") int id) {
		 employeeService.deleteById(id);
	}
	
	@GetMapping("employees/address/{id}")
	public Employee addressEmployeeById(@PathVariable(name="id") int id) {
		return employeeService.findEmployeeAddresswithAddress(id);
		
	}

	
	
	
	


}
