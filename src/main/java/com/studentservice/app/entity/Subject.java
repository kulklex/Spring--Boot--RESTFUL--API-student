package com.studentservice.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String mathematics;
	private String science;
	private String english;
	private String tech;
	private String civic;
	private String economics;
	
	@ManyToMany
	@JoinTable(
			name="students_enrolled",
			 joinColumns = @JoinColumn(name = "subject_id"),
			 inverseJoinColumns = @JoinColumn(name = "student_id")
			)
	private Set<Student> enrolledStudents = new HashSet<>();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMathematics() {
		return mathematics;
	}
	public void setMathematics(String mathematics) {
		this.mathematics = mathematics;
	}
	public String getScience() {
		return science;
	}
	public void setScience(String science) {
		this.science = science;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	public String getCivic() {
		return civic;
	}
	public void setCivic(String civic) {
		this.civic = civic;
	}
	public String getEconomics() {
		return economics;
	}
	public void setEconomics(String economics) {
		this.economics = economics;
	}
	
	
	
	public Set<Student> getStudents() {
		return enrolledStudents;
	}
	public void setStudents(Set<Student> students) {
		this.enrolledStudents = students;
	}
	public Subject(String mathematics, String science, String english, String tech, String civic, String economics) {
		super();
		this.mathematics = mathematics;
		this.science = science;
		this.english = english;
		this.tech = tech;
		this.civic = civic;
		this.economics = economics;
	}
	
	
	public Subject() {
	}
	
	
	@Override
	public String toString() {
		return "Subject [mathematics=" + mathematics + ", science=" + science + ", english=" + english + ", tech="
				+ tech + ", civic=" + civic + ", economics=" + economics + "]";
	}
	
	
	public void enrollStudent(Student student) {
		enrolledStudents.add(student);
	} 
}
