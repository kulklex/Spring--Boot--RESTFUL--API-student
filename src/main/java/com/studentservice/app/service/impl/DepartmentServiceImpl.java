package com.studentservice.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentservice.app.dtos.DepartmentDTO;
import com.studentservice.app.entity.Courses;
import com.studentservice.app.entity.Department;
import com.studentservice.app.entity.DepartmentCoursesRelationship;
import com.studentservice.app.repository.CoursesRepository;
import com.studentservice.app.repository.DepartmentCoursesRelationshipRepository;
import com.studentservice.app.repository.DepartmentRepository;
import com.studentservice.app.repository.StudentRelationshipRepository;
import com.studentservice.app.services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	private Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	
	@Autowired
	DepartmentRepository deptRepository;
	@Autowired
	DepartmentCoursesRelationshipRepository deptCoursesRelationship;
	@Autowired
	CoursesRepository courseRepository;
	@Autowired
	StudentRelationshipRepository studentRelationshipRepository;

	@Override
	public int addDepartment(DepartmentDTO department) {
		log.info("Adding Department");
		
		if (department.getName() == null || department.getName().isEmpty() || department.getFaculty() == null || department.getFaculty().isEmpty()) {
			return 3;
		}
		try {
			Department dept = deptRepository.findByName(department.getName());
			if (dept != null) {
				return 2;
			}
			Department newDept = new Department();
			newDept.setName(department.getName());
			newDept.setFaculty(department.getFaculty());
			deptRepository.save(newDept);
			return 1;
		}catch(Exception e) {
			System.out.println("Unable To Implement");
			return 0; 
		}
		
	}

	@Override
	public int addCoursesToDepartment(int deptID, List<String> courseIDs) {
		log.info("Adding Courses To Department");
		
		Optional<Department> dept = deptRepository.findById(deptID);
		if (dept.isPresent()) {
			for (String id : courseIDs) {
				DepartmentCoursesRelationship newRel = new DepartmentCoursesRelationship();
				newRel.setCourseID(Integer.parseInt(id));
				newRel.setDeptID(deptID);
				deptCoursesRelationship.save(newRel);
			}
			return 1;
		}
		return 2;
	}

	@Override
	public List<Courses> fetchAllCoursesInDepartment(int deptID) {
		log.info("Fetching All Courses In Department");
		
		List<Courses> courses = new ArrayList<>();
		Optional<Department> dept = deptRepository.findById(deptID);
		if (dept.isPresent()) {
			List<DepartmentCoursesRelationship> rels = deptCoursesRelationship.findByDeptID(deptID);
			
			for (DepartmentCoursesRelationship rel : rels) {
				Optional<Courses> course = courseRepository.findById(rel.getCourseID());
				if (!course.isPresent()) {
					continue;
				}
				courses.add(course.get());
			}
			return courses;
		}
		return new ArrayList<Courses>();
		
	}

}
