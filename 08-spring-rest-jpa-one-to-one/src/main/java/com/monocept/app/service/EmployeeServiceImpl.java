package com.monocept.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.monocept.app.entity.Address;
import com.monocept.app.entity.Employee;
import com.monocept.app.repository.AddressRepository;
import com.monocept.app.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeRepository employeeRepository;
	private AddressRepository addressRepository;
	

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, AddressRepository addressRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.addressRepository = addressRepository;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		
		if(employee.getId()!=0 && employee.getAddress().getId()!=0) {
			return employeeRepository.save(employee);
		}
		Employee employeeById = findById(employee.getId());
		if(employeeById!=null) {
			return employeeRepository.save(employee);
		}
		return employeeRepository.save(employee);

	}


	@Override
	public List<Employee> findAll() {
		
		return employeeRepository.findAll();
	}


	 @Override
	    public Employee findById(int id) {
	        Optional<Employee> employee = employeeRepository.findById(id);
	        return employee.orElse(null);
	    }


	@Override
	public void deleteById(int id) {
		employeeRepository.deleteById(id);
	}
	
	@Override
	public Employee findEmployeeAddresswithAddress(int id) {
		Optional<Address> optionalAddress = addressRepository.findById(id);
		if(optionalAddress.isPresent()) {
			Address address = optionalAddress.get();
			Employee employee = address.getEmployee();
			return employee;
		}
		return null;
	}
	
	

}
