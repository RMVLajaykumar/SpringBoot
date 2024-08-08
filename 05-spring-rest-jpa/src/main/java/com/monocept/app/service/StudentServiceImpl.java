package com.monocept.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.monocept.app.entity.Student;

import com.monocept.app.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	
	private StudentRepository studentDao;
	
	

	public StudentServiceImpl(StudentRepository studentDao) {
		super();
		this.studentDao = studentDao;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentDao.findAll();
	}

	@Override
	public Student getStudentById(int id) {
		return studentDao.findById(id).orElse(null);
		
	}

	@Override
	public Student save(Student student) {
		return studentDao.save(student);
	}

	@Override
	public void deleteStudent(int id) {
		studentDao.deleteById(id);
		
	}

}
