package com.monocept.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monocept.app.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}