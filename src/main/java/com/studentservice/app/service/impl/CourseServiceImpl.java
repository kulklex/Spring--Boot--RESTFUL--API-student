package com.studentservice.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentservice.app.dtos.CoursesDTO;
import com.studentservice.app.entity.Courses;
import com.studentservice.app.repository.CoursesRepository;
import com.studentservice.app.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	private Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Autowired
	CoursesRepository courseRepository;

	@Override
	public int addCourse(CoursesDTO courseDTO) {
		log.info("Adding Course");
		
		if (courseDTO.getCourseCode() == null || courseDTO.getCourseCode().isEmpty() || courseDTO.getCourseName() == null || courseDTO.getCourseName().isEmpty()) {
			return 3;
		}
		
		try {
		    Courses isCourse = courseRepository.findByCourseName(courseDTO.getCourseName());
		    if (isCourse != null) {
		    	return 2;
		    }
		    
		    Courses course = new Courses();
		    course.setCourseCode(courseDTO.getCourseCode());
		    course.setCourseName(courseDTO.getCourseName());
		    courseRepository.save(course);
		    return 1;
		}catch(Exception e) {
			log.error("Unable To Implement: "+ e);
			return 0;
		}
		
	}

}
