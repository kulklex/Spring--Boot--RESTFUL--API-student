package com.studentservice.app.services;

import java.util.List;

import com.studentservice.app.dtos.DepartmentDTO;
import com.studentservice.app.entity.Courses;

public interface DepartmentService {
	
	int addDepartment(DepartmentDTO department);
	
	int addCoursesToDepartment(int deptID, List<String> courseIDs);
	
	List<Courses> fetchAllCoursesInDepartment(int deptID);
	
}
