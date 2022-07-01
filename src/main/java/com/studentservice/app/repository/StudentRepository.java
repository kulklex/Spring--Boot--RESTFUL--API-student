package com.studentservice.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentservice.app.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

	Student findByFirstNameAndLastName(String firstName, String lastName);

	List<Student> findByDepartmentID(String departmentID);

}
