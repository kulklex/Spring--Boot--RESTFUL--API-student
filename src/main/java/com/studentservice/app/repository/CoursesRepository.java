package com.studentservice.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentservice.app.entity.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer>{

	Courses findByCourseNameAndCourseCode(String courseName, String courseCode);

	

}
