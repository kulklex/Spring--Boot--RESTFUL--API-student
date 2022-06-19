package com.studentservice.app.service;

import java.util.List;

import com.studentservice.app.dtos.StuDTO;
import com.studentservice.app.dtos.StudentDTO;
import com.studentservice.app.entity.School;
import com.studentservice.app.entity.Student;

public interface StudentService {
	Student addStudent(StudentDTO studentDTO);
	Student updateStudent(Student student);
	Student getStudent(int id);
	List<Student> getAllStudents();
	int deleteStudent(int id);
	Student updateFirstName(int userID, StuDTO student);
	Student updateGender(int userID, StuDTO student);
	Student updateLevel(int userID, StuDTO student);
	Student updatePhoneNumber(int userID, StuDTO student);
	Student updateEmail(int userID, StuDTO student);
	Student updateDepartment(int userID, StuDTO student);
	Student updateAge(int userID, StuDTO student);
	Student updatelastName(int userID, StuDTO student);
	Student updateMatricNumber(int userID, StuDTO student);
	Student addSchool(int id, School school);
}
