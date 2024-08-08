package com.monocept.hibernate;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.monocept.hibernate.Dao.EmployeeDao;
import com.monocept.hibernate.entity.Employee;


@SpringBootApplication
public class Application   implements CommandLineRunner{
	public EmployeeDao employeeDao;
	
	
	public Application(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//addEmployee();
		//getAllEmployees();
		//getNames();
		//getEmployeesById();
		//getEmployeeByName();
		//updateEmployee();
		//updateEmployeeWithoutMerge();
		deleteEmployee();
		//deleteEmployeeIdlessthanthree();
		
		
	}

	private void deleteEmployeeIdlessthanthree() {
		// TODO Auto-generated method stub
		employeeDao.deleteEmployee2(3);
		
	}

	private void deleteEmployee() {
		
		employeeDao.deleteEmployee(4);
		
		
	}

	private void updateEmployeeWithoutMerge() {
		
		Employee employee = new Employee(7,"mani",200.0);
		employeeDao.updateEmployee2(employee);
		
		
	}

	private void updateEmployee() {
		Employee employee = new Employee(3,"shivamani",5000.0);
		employeeDao.updateEmployee(employee);
		
	}

	private void getEmployeeByName() {
		
		List<Employee> employee=employeeDao.getEmployeeByName("ajay");
		for(Employee e:employee) {
			System.out.println(e);
		}
		
		
	}

	private void getEmployeesById() {
		int i=3;
		Employee employee= employeeDao.getEmployeebyId(i);
		if(employee!=null) {
			System.out.println(employee);
		}
		else {
			System.out.println("not found");
		}
		
	}

	private void getNames() {
		List<Employee> EmployeeList=employeeDao.getNames("Ajay");
		for(Employee e:EmployeeList) {
			System.out.println(e);
		}
	
		
	}

	private void getAllEmployees() {
List<Employee> EmployeeList=employeeDao.getAllEmployee();
		
		for(Employee e:EmployeeList) {
			System.out.println(e);
		}
		
	}

	private void addEmployee() {
		
		Employee employee = new Employee("nalla",4000.0);
		employeeDao.addEmployee(employee);
		
		
		
		
		
	}

	
	

}
