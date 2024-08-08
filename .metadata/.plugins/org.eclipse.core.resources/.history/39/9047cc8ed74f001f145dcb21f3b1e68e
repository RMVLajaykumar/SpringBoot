package com.monocept.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monocept.app.dto.EmployeeDto;
import com.monocept.app.dto.PagedResponse;
import com.monocept.app.entity.Employee;
import com.monocept.app.exception.EmployeeNotFoundException;
import com.monocept.app.repository.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeRepository employeerepository;
	

	public EmployeeServiceImpl(EmployeeRepository employeerepository) {
		super();
		this.employeerepository = employeerepository;
	}


	@Override
	public PagedResponse<EmployeeDto> getAllEmployees(int page,int size,String sortBy,String direction) {
		Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		Pageable pageable=PageRequest.of(page, size,sort);
		Page<Employee> page1=employeerepository.findAll(pageable);
		
		
		List<Employee> employee= page1.getContent();
		if(employee.isEmpty()) {
			throw new EmployeeNotFoundException("Employee  is not found");
		}
		List<EmployeeDto> employeesDTO=ConvertemployeeDtoToObjectList(page1.getContent());
		return new PagedResponse<EmployeeDto>(employeesDTO, page1.getNumber(), page1.getSize(), page1.getTotalElements(),page1.getTotalPages(),page1.isLast());

	}


	private List<EmployeeDto> ConvertemployeeDtoToObjectList(List<Employee> employee) {
		List<EmployeeDto> employeedto= new ArrayList<EmployeeDto>();
		
		for(Employee employees:employee) {
			
			EmployeeDto dto=new EmployeeDto();
			dto.setId(employees.getId());
			dto.setName(employees.getName());
			dto.setDesignation(employees.getDesignation());
			dto.setEmail(employees.getEmail());
			dto.setSalary(employees.getSalary());
			dto.setActive(employees.isActive());	
			employeedto.add(dto);
		}
		
		return employeedto;
	}


	@Override
	public EmployeeDto getEmployeeById(int id) {
	
		Employee employee= employeerepository.findById(id).orElse(null);
		if(employee==null) {
			throw new EmployeeNotFoundException("Employee with ID " + employee.getId() + " is not found");
		}
		else {
			return ConvertemployeeDtoToObject(employee);
		}
		
	}


	private EmployeeDto ConvertemployeeDtoToObject(Employee employee) {
		
			
			EmployeeDto dto=new EmployeeDto();
			dto.setId(employee.getId());
			dto.setName(employee.getName());
			dto.setDesignation(employee.getDesignation());
			dto.setEmail(employee.getEmail());
			dto.setSalary(employee.getSalary());
			dto.setActive(employee.isActive());	
			
			return dto;
			
		}
	


	@Override
	public EmployeeDto save(EmployeeDto employeedto) {

		Employee newEmployee = convertEmployeeDtoToemployee(employeedto);
		Employee save = null;
		if (newEmployee.getId() == 0) {
			newEmployee = employeerepository.save(newEmployee);
			return ConvertemployeeDtoToObject(newEmployee);
		}
		if (newEmployee.getId() != 0) {
			EmployeeDto employeeById = getEmployeeById(newEmployee.getId());
			if (employeeById == null) {
				throw new EmployeeNotFoundException("Employee with id " + newEmployee.getId() + " not found");
			}
			save = employeerepository.save(newEmployee);
		}
		return ConvertemployeeDtoToObject(save);
	}
		
		
	


	private Employee convertEmployeeDtoToemployee(EmployeeDto employeedto) {
		Employee emp=new Employee();
		
		emp.setId(employeedto.getId());
		emp.setName(employeedto.getName());
		emp.setDesignation(employeedto.getDesignation());
		emp.setEmail(employeedto.getEmail());
		emp.setSalary(employeedto.getSalary());
		emp.setActive(employeedto.isActive());

		
		return emp;
	}


	@Override
	public EmployeeDto deleteEmployee(int id) {
	    Employee employee = employeerepository.findById(id).orElse(null);
	    employeerepository.delete(employee);
	    return ConvertemployeeDtoToObject(employee);
	}



	@Override
	public EmployeeDto getEmployeebyname(String name) {
		
		Employee employee=employeerepository.findByName(name);
		if(employee==null) {
			throw new EmployeeNotFoundException("Employee with  not found");
			
		}
		else {
		return ConvertemployeeDtoToObject(employee);
		}
		
		}


	@Override
	public EmployeeDto getEmployeebyemail(String email) {
		Employee employee =employeerepository.findByEmail(email);
		if(employee==null) {
			throw new EmployeeNotFoundException("Employee with  not found");
			
		}
		else {
		return ConvertemployeeDtoToObject(employee);
		}
		
		
	}


	@Override
	public PagedResponse<EmployeeDto> getEmployeeallActive(int page,int size,String sortBy,String direction) {
		Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		Pageable pageable=PageRequest.of(page, size,sort);
       Page<Employee> page1=employeerepository.findByActiveTrue(pageable);
		List<Employee> employee= page1.getContent();
		if(employee.isEmpty()) {
			throw new EmployeeNotFoundException("Employee  is not found");
		}
		List<EmployeeDto> employeesDTO=ConvertemployeeDtoToObjectList(page1.getContent());
		return new PagedResponse<EmployeeDto>(employeesDTO, page1.getNumber(), page1.getSize(), page1.getTotalElements(),page1.getTotalPages(),page1.isLast());
		
	}
	


	@Override
	public PagedResponse<EmployeeDto> getemployeenameStartsWith(String startsWith,int page,int size,String sortBy,String direction) {
		
			Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
			Pageable pageable=PageRequest.of(page, size,sort);
	       Page<Employee> page1=employeerepository.findByNameStartingWith(startsWith,pageable);
	       List<Employee> employee= page1.getContent();
		if(employee.isEmpty()) {
			throw new EmployeeNotFoundException("Employee with  not found");
		}
		List<EmployeeDto> employeesDTO=ConvertemployeeDtoToObjectList(page1.getContent());
		return new PagedResponse<EmployeeDto>(employeesDTO, page1.getNumber(), page1.getSize(), page1.getTotalElements(),page1.getTotalPages(),page1.isLast());
		
	}


	@Override
	public Integer getCount() {
		
		int count =employeerepository.countByActiveTrue();
		if(count==0) {
			throw new EmployeeNotFoundException("No Active members");
		}
		return count;
	}




	@Override
	public PagedResponse<EmployeeDto> getEmployeeSalaryGreaterThanAndDepartment(double salary, String designation,int page,int size,String sortBy,String direction) {
		Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		Pageable pageable=PageRequest.of(page, size,sort);
		 Page<Employee> page1=employeerepository.findBySalaryGreaterThanAndDesignation(salary, designation,pageable);
	
		 List<Employee> employee= page1.getContent();
			if(employee.isEmpty()) {
				throw new EmployeeNotFoundException("Employee with  not found");
			}
			List<EmployeeDto> employeesDTO=ConvertemployeeDtoToObjectList(page1.getContent());
			return new PagedResponse<EmployeeDto>(employeesDTO, page1.getNumber(), page1.getSize(), page1.getTotalElements(),page1.getTotalPages(),page1.isLast());
	}


	@Override
	public int getCountDepartment(String designation) {
		
		int employee1 =employeerepository.countByDesignation(designation);
		if(employee1==0) {
			throw new EmployeeNotFoundException("Employee with name letter not found");
		}
		
		return employee1;
	}


	@Override
	public PagedResponse<EmployeeDto> getBetweenSalaryRange(double startSalary, double endSalary,int page,int size,String sortBy,String direction) {
		Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		Pageable pageable=PageRequest.of(page, size,sort);
		 Page<Employee> page1=employeerepository.findBySalaryBetween(startSalary, endSalary,pageable);
		 List<Employee> employee= page1.getContent();
			if(employee.isEmpty()) {
				throw new EmployeeNotFoundException("Employee with  not found");
			}
			List<EmployeeDto> employeesDTO=ConvertemployeeDtoToObjectList(page1.getContent());
			return new PagedResponse<EmployeeDto>(employeesDTO, page1.getNumber(), page1.getSize(), page1.getTotalElements(),page1.getTotalPages(),page1.isLast());
		
	
	}


	@Override
	public int countSalaryGreaterthan(double salary) {
		
		return employeerepository.countBySalaryGreaterThan(salary);
	}

}
