package com.monocept.app.service;

import java.util.List;

import com.monocept.app.dto.EmployeeDto;
import com.monocept.app.dto.PagedResponse;
import com.monocept.app.entity.Employee;



public interface EmployeeService {

	PagedResponse<EmployeeDto> getAllEmployees(int page,int size,String sortBy,String direction);

	EmployeeDto getEmployeeById(int id);

	EmployeeDto save(EmployeeDto employeedto);

	EmployeeDto deleteEmployee(int id);


	EmployeeDto getEmployeebyname(String name);

	EmployeeDto getEmployeebyemail(String email);

	PagedResponse<EmployeeDto> getEmployeeallActive(int page,int size,String sortBy,String direction);

	PagedResponse<EmployeeDto> getemployeenameStartsWith(String startsWith,int page,int size,String sortBy,String direction);

	Integer getCount();

	PagedResponse<EmployeeDto> getEmployeeSalaryGreaterThanAndDepartment(double salary, String designation,int page,int size,String sortBy,String direction);

	int getCountDepartment(String designation);

	PagedResponse<EmployeeDto> getBetweenSalaryRange(double startSalary, double endSalary,int page,int size,String sortBy,String direction);

	int countSalaryGreaterthan(double salary);


	
}
