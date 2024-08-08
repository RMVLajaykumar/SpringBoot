package com.monocept.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.monocept.app.Dto.CourseDTO;
import com.monocept.app.Dto.StudentDTO;
import com.monocept.app.entity.Course;
import com.monocept.app.entity.Student;
import com.monocept.app.repository.CourseRepository;
import com.monocept.app.repository.StudentRepository;

import jakarta.validation.Valid;



@Service
public class StudentCourseServiceImpl implements StudentCourseService{
	private StudentRepository studentRepository;
	private CourseRepository courseRepository;
	
	
	public StudentCourseServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
		super();
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	}


	@Override
	public StudentDTO addStudent(@Valid Student student) {
		return convertStudentToStudentResponseDTO(studentRepository.save(student));

	}


	private StudentDTO convertStudentToStudentResponseDTO(Student student) {
		
		StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setStudentName(student.getName());
        
        List<CourseDTO> courses = new ArrayList<>();
        for (Course c : student.getCourses()) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(c.getId());
            courseDTO.setTitle(c.getTitle());
            courses.add(courseDTO);
        }
        studentDTO.setCourses(courses);
		return studentDTO;
	}


	


	@Override
	public List<StudentDTO> getStudents() {
	
		List<Student> students = studentRepository.findAll();

		return convertStudentToStudentResponseDTO(students);
	}


	@Override
	public StudentDTO getStudentById(long id) {
		Student student = studentRepository.findById(id).orElse(null);
		return convertStudentToStudentResponseDTO(student);

		

		
	}


	private List<StudentDTO> convertStudentToStudentResponseDTO(List<Student> students) {
		  List<StudentDTO> studentsDTO = new ArrayList<>();
		    for (Student s : students) {
		        StudentDTO studentDTO = new StudentDTO();
		        studentDTO.setId(s.getId());
		        studentDTO.setStudentName(s.getName());
		        
		        List<CourseDTO> courses = new ArrayList<>();
		        for (Course c : s.getCourses()) {
		            CourseDTO courseResponseDTO = new CourseDTO();
		            courseResponseDTO.setId(c.getId());
		            courseResponseDTO.setTitle(c.getTitle());
		            courses.add(courseResponseDTO);
		        }
		        studentDTO.setCourses(courses);;
		        studentsDTO.add(studentDTO);
		        
		    }
		    return studentsDTO;
		}

	


	@Override
	public String deleteStudentById(long id) {
		Student student = studentRepository.findById(id).orElse(null);
		if(student==null) {
			return "No Student Found";
		}
		studentRepository.delete(student);
		return "Deleted Succesfully";

	}


	@Override
	public CourseDTO addCourse(@Valid Course course) {
		return convertCourseToCourseDTO(courseRepository.save(course));

	}


	private CourseDTO convertCourseToCourseDTO(Course course) {
		CourseDTO courseResponseDTO=new CourseDTO();
		courseResponseDTO.setId(course.getId());
		courseResponseDTO.setTitle(course.getTitle());
		List<StudentDTO> students=new ArrayList<StudentDTO>();
		for(Student s:course.getStudents()) {
			StudentDTO studentResponseDTO=new StudentDTO();
			studentResponseDTO.setId(s.getId());
			studentResponseDTO.setStudentName(s.getName());
			students.add(studentResponseDTO);
		}
		courseResponseDTO.setStudents(students);
		return courseResponseDTO;
	

	}


	@Override
	public List<CourseDTO> getCourses() {
		List<Course> courses = courseRepository.findAll();
		return convertCoursesToCourseResponseDTO(courses);
	}



	private List<CourseDTO> convertCoursesToCourseResponseDTO(List<Course> courses) {
		List<CourseDTO> coursesDTO=new ArrayList<CourseDTO>();
		for(Course c:courses) {
			CourseDTO courseResponseDTO=new CourseDTO();
			courseResponseDTO.setId(c.getId());
			courseResponseDTO.setTitle(c.getTitle());
			List<StudentDTO> students=new ArrayList<StudentDTO>();
			for(Student s:c.getStudents()) {
				StudentDTO studentResponseDTO=new StudentDTO();
				studentResponseDTO.setId(s.getId());
				studentResponseDTO.setStudentName(s.getName());
				students.add(studentResponseDTO);
			}
			courseResponseDTO.setStudents(students);
			coursesDTO.add(courseResponseDTO);
		}
		return coursesDTO;
	}

	


	@Override
	public CourseDTO geCourseById(long id) {
		Course course = courseRepository.findById(id).orElse(null);
		if(course==null) {
			return null;
		}
		return convertCourseToCourseDTO(course);
	}


	@Override
	public String  deleteCourseById(long id) {
		
		    Course course = courseRepository.findById(id).orElse(null);
		    if (course != null) {
		        for (Student student : course.getStudents()) {
		            student.getCourses().remove(course);
		            studentRepository.save(student);
		        }
		        courseRepository.deleteById(id);
		        return "Course deleted successfully";
		    } else {
		        return "Course not found";
		    }
		}

	@Override
	 public StudentDTO addCourseToStudent(long studentId, long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null) {
            Course course = courseRepository.findById(courseId).orElse(null);
            if (course != null) {
                student.addCourse(course);
                studentRepository.save(student);
                return convertStudentToStudentResponseDTO(student);
            }
        }
        return null;
    }


	
	
		



	@Override
	public StudentDTO deleteCourseToStudent(long studentId, long courseId) {
		
		 Student student = studentRepository.findById(studentId).orElse(null);
	        if (student != null) {
	            Course course = courseRepository.findById(courseId).orElse(null);
	            if (course != null) {
	                student.removeCourse(course);
	                studentRepository.save(student);
	                return convertStudentToStudentResponseDTO(student);
	            }
	        }
	        return null;
	    }


	
}