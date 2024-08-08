package com.monocept.hibernate.dao;

import java.util.List;

import com.monocept.hibernate.entity.Student;

public interface StudentDao {
	
	public void save(Student student);

	public List<Student> getAllStudents();

	public Student getStudentId(int i);

	public  List<Student> getStudentByName(String first_name) ;

	public List<Student> getStudentsbyfirstandLastname(String string, String string2);

	//public void updateStudent(Student s);

	public void deleteStudent(int i);

	public void updated(Student s);

	public void DeleteStudentIdLessThanTwo(int i);

	

	

	

	
	

}
