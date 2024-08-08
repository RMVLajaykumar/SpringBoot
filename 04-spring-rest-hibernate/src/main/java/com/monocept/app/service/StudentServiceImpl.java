package com.monocept.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.monocept.app.dao.StudentDao;
import com.monocept.app.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	
	private StudentDao studentDao;
	

	public StudentServiceImpl(StudentDao studentDao) {
		super();
		this.studentDao = studentDao;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	@Override
	public Student getStudentById(int id) {
		return studentDao.getStudentById(id);
	}

	@Override
	public Student save(Student student) {
		return studentDao.save(student);
	}

	@Override
	public void deleteStudent(int id) {
		studentDao.deleteStudent(id);
		
	}

}
