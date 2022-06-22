package com.studentservice.app.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentservice.app.dtos.CoursesDTO;
import com.studentservice.app.entity.Courses;
import com.studentservice.app.repository.CoursesRepository;
import com.studentservice.app.services.CourseService;



public class CoursesServiceImplementation implements CourseService {
	

	private Logger log = LoggerFactory.getLogger(CoursesServiceImplementation.class);
	
	@Autowired
	CoursesRepository coursesRepository;
	
	@Override
	public int addCourse(CoursesDTO courseDTO) {
		log.info("Add Course");
		
		if(courseDTO.getCourseCode() == null || courseDTO.getCourseCode().isEmpty() 
				|| courseDTO.getCourseName() == null || courseDTO.getCourseName().isEmpty())
		{
			return 3;
		}
		try {
			//check if it exists 
			Courses ifCourseExists = coursesRepository.findByCourseNameAndCourseCode(courseDTO.getCourseName(), courseDTO.getCourseCode()); 
			if(ifCourseExists != null ) {
				return 2;
			}
			Courses course = new Courses();
			course.setCourseName(courseDTO.getCourseName());
			course.setCourseCode(courseDTO.getCourseCode());
			coursesRepository.save(course);
			return 1;
		} catch(Exception e) {
			log.error("Unable to implement: " + e.getMessage());
			return 0;
		}
	}

}
