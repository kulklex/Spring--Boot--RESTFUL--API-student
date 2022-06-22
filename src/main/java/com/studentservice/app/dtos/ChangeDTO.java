package com.studentservice.app.dtos;

import java.util.List;

import com.studentservice.app.entity.Courses;

public class ChangeDTO {
	private int deptID;
	private int studentID;
	private List<Courses> courses;
	
	public int getDeptID() {
		return deptID;
	}
	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public List<Courses> getCourses() {
		return courses;
	}
	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}
	
	
}
