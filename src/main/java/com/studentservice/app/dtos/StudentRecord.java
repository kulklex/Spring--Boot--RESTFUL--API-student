package com.studentservice.app.dtos;

import java.util.List;

import com.studentservice.app.entity.Student;

public class StudentRecord {
	
	private Student student;
	private List<String> courseNames;
	private String department;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<String> getCourseNames() {
		return courseNames;
	}
	public void setCourseNames(List<String> courseNames) {
		this.courseNames = courseNames;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}
