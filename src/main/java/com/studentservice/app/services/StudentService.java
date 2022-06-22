package com.studentservice.app.services;

import java.util.List;

import com.studentservice.app.dtos.ChangeDTO;
import com.studentservice.app.dtos.StuDTO;
import com.studentservice.app.dtos.StudentDTO;
import com.studentservice.app.dtos.StudentRecord;
import com.studentservice.app.entity.Courses;
import com.studentservice.app.entity.Student;

public interface StudentService {
	
	int addStudent(StudentDTO studentDTO);
	int updateStudent(StuDTO studentDTO);
	int addStudentCourses(List<String> courseIDs, int studentID);
	StudentRecord getStudent(int studentID);
	List<Student> getStudentsByDepartmentID(int departmentID);
	List<Courses> getCoursesByStudentID(int studentID);
	int changeStudentStatus(int studentID, boolean status);
	int paySchoolFees(int studentID);
	int changeDepartment(ChangeDTO changeDTO);
	
}
