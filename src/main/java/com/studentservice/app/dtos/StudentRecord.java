package com.studentservice.app.dtos;

import java.util.List;

import com.studentservice.app.entity.Student;

public class StudentRecord {
	
	private Student student;
	private List<String> courseNames;
	private String department;
	private String faculty;
	
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
	/**
	 * @return the faculty
	 */
	public String getFaculty() {
		return faculty;
	}
	/**
	 * @param faculty the faculty to set
	 */
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	
	
}
