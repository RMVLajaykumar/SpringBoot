package com.monocept.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.app.Dto.CourseDTO;
import com.monocept.app.Dto.StudentDTO;
import com.monocept.app.entity.Course;
import com.monocept.app.entity.Student;
import com.monocept.app.service.StudentCourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class StudentCourseController {
	
	private StudentCourseService studentCourseService;
	
	
	
	public StudentCourseController(StudentCourseService studentCourseService) {
		super();
		this.studentCourseService = studentCourseService;
	}



	@PostMapping("students")
	public StudentDTO addStudent( @RequestParam(name="name")String name){
		Student student=new Student();
		student.setName(name);
		return studentCourseService.addStudent(student);
		
	}
	
	
	@GetMapping("students")
	public List<StudentDTO> getAllStudents() {
		return studentCourseService.getStudents();
	}
	
	
	@GetMapping("students/{id}")
	public StudentDTO getStudentById(@PathVariable(name="id")long id) {
		return studentCourseService.getStudentById(id);
	}
	
	
	@DeleteMapping("students/{id}")
	public String deleteStudentById(@PathVariable(name="id")long id) {
		 return studentCourseService.deleteStudentById(id);
		
		
	}
	
	@PostMapping("courses")
	public CourseDTO addCourse(@RequestParam(name="title") String title){
		Course course=new Course();
		course.setTitle(title);
		return studentCourseService.addCourse(course);
		
	}
	@GetMapping("courses")
	public List<CourseDTO> getAllCourses() {
		return studentCourseService.getCourses();
	}
	@GetMapping("courses/{id}")
	public CourseDTO getCourseById(@PathVariable(name="id")long id) {
		return studentCourseService.geCourseById(id);
	}
	
	@DeleteMapping("courses/{id}")
	public String deleteCourseById(@PathVariable(name="id")long id) {
		return studentCourseService.deleteCourseById(id);
		
	}
	@PostMapping("students/{studentId}/courses/{courseId}")
    public StudentDTO addCourseToStudent(@PathVariable(name = "studentId") long studentId, @PathVariable(name = "courseId") long courseId) {
        return studentCourseService.addCourseToStudent(studentId, courseId);
	}
	@DeleteMapping("students/{studentId}/courses/{courseId}")
    public StudentDTO deleteCourseToStudent(@PathVariable(name = "studentId") long studentId, @PathVariable(name = "courseId") long courseId) {
        return studentCourseService.deleteCourseToStudent(studentId, courseId);
	}
	
}
