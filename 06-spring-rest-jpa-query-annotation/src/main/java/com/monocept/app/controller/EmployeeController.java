package com.monocept.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.app.dto.EmployeeDto;
import com.monocept.app.dto.PagedResponse;
import com.monocept.app.entity.Employee;

import com.monocept.app.exception.EmployeeNotFoundException;

import com.monocept.app.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping("/employees")
	public ResponseEntity<PagedResponse<EmployeeDto>> testfunc(@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="sortBy",defaultValue = "id")String sortBy,
			@RequestParam(name="direction",defaultValue="asc")String direction)
	
	
			
 {
		PagedResponse<EmployeeDto> employee = employeeService.getAllEmployees(page,size,sortBy,direction);

		return new ResponseEntity<PagedResponse<EmployeeDto>>(employee, HttpStatus.OK);

	}

	@GetMapping("employees/{sid}")
	public ResponseEntity<EmployeeDto> getEmployeeByID(@PathVariable(name = "sid") int id) {
		EmployeeDto employeedto = employeeService.getEmployeeById(id);
		return new ResponseEntity<EmployeeDto>(employeedto, HttpStatus.OK);

	}

	@PutMapping("/employees")
	public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestBody EmployeeDto employeedto) {
		EmployeeDto tempemployee = employeeService.save(employeedto);
		return new ResponseEntity<EmployeeDto>(tempemployee, HttpStatus.OK);

	}

	@DeleteMapping("employees/{sid}")

	public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable(name = "sid") int id) {
		EmployeeDto employeedto = employeeService.deleteEmployee(id);
		return new ResponseEntity<EmployeeDto>(employeedto, HttpStatus.OK);
	}

	@PostMapping("/employees")
	public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeedto) {
		EmployeeDto addedemployee = employeeService.save(employeedto);
		return new ResponseEntity<EmployeeDto>(addedemployee, HttpStatus.CREATED);
	}

	@GetMapping("employees/name/{name}")
	public ResponseEntity<EmployeeDto> getEmployeeByName(@PathVariable(name = "name") String name) {
		EmployeeDto employee1 = employeeService.getEmployeebyname(name);
		return new ResponseEntity<EmployeeDto>(employee1, HttpStatus.OK);
	}

	@GetMapping("employees/email/{email}")
	public ResponseEntity<EmployeeDto> getEmployeeByEmail(@PathVariable(name = "email") String email) {
		EmployeeDto employee1 = employeeService.getEmployeebyemail(email);
		return new ResponseEntity<EmployeeDto>(employee1, HttpStatus.OK);

	}

	@GetMapping("employees/allactive")
	public ResponseEntity<PagedResponse<EmployeeDto>>getEmployeeAllActive(@RequestParam(name="page",defaultValue = "0") int page,
	@RequestParam(name="size",defaultValue = "5")int size,
	@RequestParam(name="sortBy",defaultValue = "id")String sortBy,
	@RequestParam(name="direction",defaultValue="asc")String direction) {
		PagedResponse<EmployeeDto> employee1 = employeeService.getEmployeeallActive(page,size,sortBy,direction);

		return new ResponseEntity<PagedResponse<EmployeeDto>>(employee1, HttpStatus.OK);
	}

	@GetMapping("employees/StartsWith/{StartsWith}")

	public ResponseEntity<PagedResponse<EmployeeDto>> getEmployeeStartsWith(
			@PathVariable(name = "StartsWith") String StartsWith,
			@RequestParam(name="page",defaultValue = "0") int page,
					@RequestParam(name="size",defaultValue = "5")int size,
					@RequestParam(name="sortBy",defaultValue = "id")String sortBy,
					@RequestParam(name="direction",defaultValue="asc")String direction) {

		PagedResponse<EmployeeDto> employee1 = employeeService.getemployeenameStartsWith(StartsWith,page,size,sortBy,direction);
		return new ResponseEntity<PagedResponse<EmployeeDto>>(employee1, HttpStatus.OK);

	}
	@GetMapping("employees/Count")

	public Integer getCount() {

		Integer count = employeeService.getCount();
		return count;
	}

	@GetMapping("employees/salaryGreaterThanAndDepartement")
	public ResponseEntity<PagedResponse<EmployeeDto>> getEmployeeSalaryGreaterThanAndDepartment(
			@RequestBody EmployeeDto employeedto,
			@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="sortBy",defaultValue = "id")String sortBy,
			@RequestParam(name="direction",defaultValue="asc")String direction) {
		PagedResponse<EmployeeDto> employees = employeeService.getEmployeeSalaryGreaterThanAndDepartment(employeedto.getSalary(),
				employeedto.getDesignation(),page,size,sortBy,direction);
		return new ResponseEntity<PagedResponse<EmployeeDto>>(employees, HttpStatus.OK);
	}

	@GetMapping("employees/countDepartment/{designation}")
	public Integer getCountDepartment(@PathVariable(name = "designation") String designation) {

		int count = employeeService.getCountDepartment(designation);
		return count;
	}

	@GetMapping("employees/salaryBetween/{startSalary}/{endSalary}")

	public ResponseEntity<PagedResponse<EmployeeDto>> getBetweenSalaryRange(
			@PathVariable(name = "startSalary") double startSalary,
			@PathVariable(name = "endSalary") double endSalary,
			@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="sortBy",defaultValue = "id")String sortBy,
			@RequestParam(name="direction",defaultValue="asc")String direction) {

		PagedResponse<EmployeeDto> employee1 = employeeService.getBetweenSalaryRange(startSalary, endSalary,page,size,sortBy,direction);
		return new ResponseEntity<PagedResponse<EmployeeDto>>(employee1, HttpStatus.OK);

	}

	@GetMapping("employees/countBySalaryGreaterThan/{salary}")
	public ResponseEntity<Integer> getEmployeeCountBySalaryGreaterThan(@PathVariable double salary) {
		int count = employeeService.countSalaryGreaterthan(salary);
		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}

}
