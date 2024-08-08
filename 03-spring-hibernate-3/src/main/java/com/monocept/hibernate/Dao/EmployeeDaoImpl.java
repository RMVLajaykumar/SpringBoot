package com.monocept.hibernate.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.monocept.hibernate.entity.Employee;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	EntityManager entityManager;

	

	@Override
	@Transactional
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		this.entityManager.persist(employee);
		
	}



	public EmployeeDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}



	@Override
	public List<Employee> getAllEmployee() {
		TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e",Employee.class);
		List<Employee> EmployeeList=query.getResultList();
		return EmployeeList;
	}



	@Override
	public List<Employee> getNames(String name) {
		TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e where name=?1",Employee.class);
		query.setParameter(1,name);
		List<Employee> EmployeeList=query.getResultList();
		return EmployeeList;
		
		
	}



	@Override
	public Employee getEmployeebyId(int i) {
		Employee employee=entityManager.find(Employee.class,i);
		return employee;
	}



	@Override
	public List<Employee> getEmployeeByName(String name) {
		TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e where name=?1",Employee.class);
		query.setParameter(1,name);
		List<Employee> EmployeeList=query.getResultList();
		return EmployeeList;
		
		
	}



	@Override
	@Transactional
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Employee employee2 = entityManager.find( Employee.class,employee.getId());
		if(employee2!=null) {
			this.entityManager.merge(employee);
		}
		else {
			System.out.println("shivamani has less than 5000");
		}
		
		
	}



	@Override
	@Transactional
	public void updateEmployee2(Employee employee) {
		
		Query query = entityManager.createQuery("update Employee e set e.name=?1,e.salary=?2 where e.id=?3");
		query.setParameter(1, employee.getName());
		query.setParameter(2, employee.getSalary());
		query.setParameter(3, employee.getId());
		
		int executeUpdate = query.executeUpdate();
		System.out.println(executeUpdate);
		
		
	
		
	}



	@Override
	@Transactional
	public void deleteEmployee(int id) {
//		Query query = entityManager.createQuery("delete from Employee e where id=?1");
//		query.setParameter(1, id);
//		int executeUpdate = query.executeUpdate();
//		System.out.println(executeUpdate);
		Employee employee = entityManager.find(Employee.class,id);
		if(employee!=null) {
			entityManager.remove(employee);
		}
		else {
			System.out.println("employee not found");
			
		}
		
		
		
	}



	@Override
	@Transactional
	public void deleteEmployee2(int id) {
		Query query = entityManager.createQuery("delete from Employee e where id<=?1");
		query.setParameter(1, id);
		int executeUpdate = query.executeUpdate();
		System.out.println(executeUpdate);
		
		
		
		
		
	}



	
	

}
