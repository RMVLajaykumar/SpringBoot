package com.monocept.app.service;

import java.util.List;

import com.monocept.app.Dto.CourseDTO;
import com.monocept.app.Dto.StudentDTO;
import com.monocept.app.entity.Course;
import com.monocept.app.entity.Student;

import jakarta.validation.Valid;

public interface StudentCourseService {

	StudentDTO addStudent(@Valid Student student);

	List<StudentDTO> getStudents();

	StudentDTO getStudentById(long id);

	String deleteStudentById(long id);

	CourseDTO addCourse(@Valid Course course);

	List<CourseDTO> getCourses();

	CourseDTO geCourseById(long id);

	public String deleteCourseById(long id);

	StudentDTO addCourseToStudent(long studentId, long courseId);

	StudentDTO deleteCourseToStudent(long studentId, long courseId);

}
