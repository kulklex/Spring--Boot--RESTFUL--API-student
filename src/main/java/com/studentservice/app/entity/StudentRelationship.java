package com.studentservice.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StudentRelationship {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private int studentID;
	private int courseID;
	private int departmentID;
	private Boolean status;
	private int tutorID;
	private boolean isSchoolFeesPaid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public int getTutorID() {
		return tutorID;
	}
	public void setTutorID(int tutorID) {
		this.tutorID = tutorID;
	}
	public boolean isSchoolFeesPaid() {
		return isSchoolFeesPaid;
	}
	public void setSchoolFeesPaid(boolean isSchoolFeesPaid) {
		this.isSchoolFeesPaid = isSchoolFeesPaid;
	}
	
	
}
