package com.monocept.hibernate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.monocept.hibernate.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
public class StudentDaoImpl implements StudentDao {
	EntityManager entityManager;
	
	
	public StudentDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}


	@Override
	@Transactional
	public void save(Student student) {
		// TODO Auto-generated method stub
		 this.entityManager.persist(student);
		 
		
		 
	}


	@Override
	public List<Student> getAllStudents() {
		TypedQuery<Student> query = entityManager.createQuery("select s from Student s",Student.class);
		List<Student> studentList=query.getResultList();
		
		return studentList;
	}


	@Override
	public Student getStudentId(int i) {
		
		// TODO Auto-generated method stub
		Student student = entityManager.find(Student.class,i);
		return student;
	}


	@Override
	public List<Student> getStudentByName(String first_name) {
		TypedQuery<Student> query = entityManager.createQuery("select s from Student s where first_name=?1",Student.class);
		query.setParameter(1, first_name);
	
		
		return query.getResultList();
	}


	@Override
	public List<Student> getStudentsbyfirstandLastname(String first_name, String last_name) {
		TypedQuery<Student> query = entityManager.createQuery("select s from Student s where first_name=?1 and last_name=?2",Student.class);
		query.setParameter(1, first_name);
		query.setParameter(2, last_name);
	
	
		
		return query.getResultList();
	}


//	@Override
//	@Transactional
//	public void updateStudent(Student s) {
////		Student student=entityManager.find(Student.class, s.getId());
////		if(student!=null) {
////			this.entityManager.merge(s);
////		}
////		else {
////			System.out.println("Student does not exist: "+s.getId());
////		}
	
//	}


	@Override
	@Transactional
	public void deleteStudent(int i) {
		Student student=entityManager.find(Student.class, i);
		if(student!=null) {
			this.entityManager.remove(student);
		}
		else {
			System.out.println("id does not exist");
		}
		
		
		
	}


	@Override
	@Transactional
	public void updated(Student s) {
		  Query query = entityManager.createQuery("update Student s set s.first_name=?1,s.last_name=?2,s.email=?3 where s.id=?4");
		  
			query.setParameter(1, s.getFirst_name());
			query.setParameter(2, s.getLast_name());
			query.setParameter(3, s.getEmail());
			query.setParameter(4, s.getId());
			int res = query.executeUpdate();
			System.out.println(res);
		}


	@Override
	@Transactional
	public void DeleteStudentIdLessThanTwo(int id) {
		Query query=entityManager.createQuery("delete from Student s  where s.id<=?1");
		query.setParameter(1, id);
		int res = query.executeUpdate();
		System.out.println(res);
		
		
		
	}
	}



