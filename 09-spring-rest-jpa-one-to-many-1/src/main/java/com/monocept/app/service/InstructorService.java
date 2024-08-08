package com.monocept.app.service;

import java.util.List;

import com.monocept.app.entity.Course;
import com.monocept.app.entity.Instructor;

public interface InstructorService {
	
	public List<Instructor> findAll();

	public Instructor save(Instructor instructor);

	public Instructor findInstructorById(int id);

	public void deleteById(int id);

	public Instructor addCourseToInstructor(int instructorId, int courseId);

	public Instructor removeCoursefromIstructor(int instructorId, int courseId);

	public Course saveCourse(Course course);

}
