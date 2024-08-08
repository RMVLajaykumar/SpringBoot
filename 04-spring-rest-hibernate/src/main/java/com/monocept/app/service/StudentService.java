package com.monocept.app.service;

import java.util.List;

import com.monocept.app.entity.Student;

public interface StudentService {
	
	
	List<Student> getAllStudents();

	Student getStudentById(int id);

	Student save(Student student);

	void deleteStudent(int id);

}
