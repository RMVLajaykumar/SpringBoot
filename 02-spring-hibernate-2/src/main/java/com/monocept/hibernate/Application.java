package com.monocept.hibernate;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.monocept.hibernate.dao.StudentDao;
import com.monocept.hibernate.entity.Student;

@SpringBootApplication
public class Application implements CommandLineRunner{
       
	 public StudentDao studentDao;
	 
	public Application(StudentDao studentDao) {
		super();
		this.studentDao = studentDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//addStudent();
		//getAllStudents();
		//getStudentbyId();
		//getStudentByName();
	//	getStudentsbyfirstandLastname();
		
		//deleteStudent();
		
		updateStudentByQery();
		//DeleteStudentIdLessThanTwo();
		
	}

	private void DeleteStudentIdLessThanTwo() {
		studentDao.DeleteStudentIdLessThanTwo(3);
		
	}

	private void updateStudentByQery() {
		Student s = new Student(4,"vv@gmail.com","varish","mani");
		studentDao.updated(s);
		
		
	}

	private void deleteStudent() {
		studentDao.deleteStudent(3);
		
		
	}



	private void getStudentsbyfirstandLastname() {
		List<Student>studentList=studentDao.getStudentsbyfirstandLastname("ajay","kumar");
		for(Student s:studentList) {
			System.out.println(s);
		}
	
		
	}

	private void getStudentByName() {
	List<Student>studentList=studentDao.getStudentByName("ajay");
	for(Student s:studentList) {
		System.out.println(s);
	}
		
	}

	private void getStudentbyId() {
		// TODO Auto-generated method stub
		int i=3;
		Student student = studentDao.getStudentId(i);
		if(student!=null)
			System.out.println(student);
		else {
			System.out.println("no student found");
		}
		
		
		
	}

	private void getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> studentList=studentDao.getAllStudents();
		
		for(Student s:studentList) {
			System.out.println(s);
		}
	}

	private void addStudent() {
		// TODO Auto-generated method stub
		Student student=new Student("vv@gmail.com","varish","valleti");
		studentDao.save(student);
	}
	


}
