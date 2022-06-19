package com.studentservice.app.service;

import java.util.List;

import com.studentservice.app.dtos.SchoolDTO;
import com.studentservice.app.entity.School;

public interface SchoolService {
	School addSchool(SchoolDTO school);
	School findOneSchool(int id);
	List<School> getAllAvaliableSchools();
	School updateSchool(School school);
	int deleteSchool(int id);
}
