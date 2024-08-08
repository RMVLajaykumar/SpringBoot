package com.monocept.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.app.entity.Course;
import com.monocept.app.entity.Instructor;
import com.monocept.app.service.InstructorService;

@RestController
@RequestMapping("/api")
public class InstructorController {
	
	private InstructorService instructorService;
	
	
	
	public InstructorController(InstructorService instructorService) {
		super();
		this.instructorService = instructorService;
	}



	@GetMapping("instructors")
	public List<Instructor> getAllInstructor(){
		return instructorService.findAll();
	}
	
	@PostMapping("add-instructor")
	public Instructor addInstructor(@RequestBody Instructor instructor) {
		return instructorService.save(instructor);
	}
	@GetMapping("instructors/{id}")
	public Instructor getInstructorById(@PathVariable(name="id")int id){
		return instructorService.findInstructorById(id);
	}
	@DeleteMapping("delete-instructor/{id}")
		public void deleteInstructorById(@PathVariable(name="id")int id) {
		 instructorService.deleteById(id);
		}
	@PutMapping("update-instructor")
	 public Instructor updateInstructor(@RequestBody Instructor instructor) {
	
		return instructorService.save(instructor);
	}
	 
	@PutMapping("{instId}/course/{courseId}")
	public ResponseEntity<Instructor> addCouseToInstructor
	(@PathVariable(name="instId") int instructorId,@PathVariable(name="courseId")int courseId){
		Instructor instructor=instructorService.addCourseToInstructor(instructorId,courseId);
		return new ResponseEntity<Instructor>(instructor,HttpStatus.OK);
	}
	
	@PutMapping("{instId}/remove-course/{courseId}")
	public ResponseEntity<Instructor> removeCouseToInstructor
	(@PathVariable(name="instId") int instructorId,@PathVariable(name="courseId")int courseId){
		Instructor instructor=instructorService.removeCoursefromIstructor(instructorId,courseId);
		return new ResponseEntity<Instructor>(instructor,HttpStatus.OK);
	}
	
	@PostMapping("add-course")
	public Course addCourse(@RequestBody Course course) {
		return instructorService.saveCourse(course);
	}
	
	
	}


