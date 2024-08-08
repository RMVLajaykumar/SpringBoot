package com.monocept.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.monocept.app.entity.Course;
import com.monocept.app.entity.Instructor;
import com.monocept.app.repository.CourseRepository;
import com.monocept.app.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService{
	
	private InstructorRepository instructorRepository;
	private CourseRepository courseRepository;
	

	public InstructorServiceImpl(InstructorRepository instructorRepository, CourseRepository courseRepository) {
		super();
		this.instructorRepository = instructorRepository;
		this.courseRepository = courseRepository;
	}


	@Override
	public List<Instructor> findAll() {
		
		return instructorRepository.findAll();
	}


	@Override
	public Instructor save(Instructor instructor) {
		if(instructor.getId()==0) {
			return instructorRepository.save(instructor);
		}
		Instructor instructorById = findInstructorById(instructor.getId());
		if(instructorById!=null) {
			return instructorRepository.save(instructor);
		}
		return null;
	}

	@Override
	public Instructor findInstructorById(int id) {
		
		Optional<Instructor> byId = instructorRepository.findById(id);
		 return byId.orElse(null);
	}

	@Override
	public void deleteById(int id) {
		instructorRepository.deleteById(id);
	}


	@Override
	public Instructor addCourseToInstructor(int instructorId, int courseId) {
		Instructor instructor = instructorRepository.findById(instructorId).orElse(null);
		if(instructor!=null) {
			Course course=courseRepository.findById(courseId).orElse(null);
			if(course!=null) {
				if(course.getInstructor()==null) {
					instructor.addCourse(course);
					course.setInstructor(instructor);
					instructorRepository.save(instructor);
					return instructor;
				}
				else {
					System.out.println("Instructor already to the course");
				}
			}
		}
		return null;
	
	}


	@Override
	public Instructor removeCoursefromIstructor(int instructorId, int courseId) {
		Instructor instructor = instructorRepository.findById(instructorId).orElse(null);
		if(instructor!=null) {
			Course course=courseRepository.findById(courseId).orElse(null);
			if(course!=null) {
					instructor.remove(course);
					courseRepository.save(course);
					return instructorRepository.save(instructor);
				}
				else {
					System.out.println("Instructor does not teach this course");
				}
			}
		
		
		return null;
	}


	@Override
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	
}
	
