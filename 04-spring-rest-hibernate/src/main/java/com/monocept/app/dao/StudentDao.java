package com.monocept.app.dao;

import java.util.List;

import com.monocept.app.entity.Student;

public interface StudentDao {

	List<Student> getAllStudents();

	Student getStudentById(int id);

	Student save(Student student);

	void deleteStudent(int id);
	
	
	
	
	

}
