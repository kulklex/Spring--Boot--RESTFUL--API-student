package com.studentservice.app.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentservice.app.dtos.DepartmentDTO;
import com.studentservice.app.entity.Department;
import com.studentservice.app.repository.DepartmentRepository;
import com.studentservice.app.services.DepartmentService;



public class DepartmentServiceImplementation implements DepartmentService {

	private Logger log = LoggerFactory.getLogger(DepartmentServiceImplementation.class);
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public int addDepartment(DepartmentDTO departmentDTO) {
		if(departmentDTO.getName() == null || departmentDTO.getFaculty() == null || departmentDTO.getFaculty().isEmpty() || departmentDTO.getName().isEmpty()) {
			return 3;
		}
		
		try {
//			Department ifDepartmentExists = departmentRepository.findByFirstName(departmentDTO.getName());
//			if(ifDepartmentExists != null) return 2;
//			
			Department department = new Department();
			department.setName(departmentDTO.getName());
			department.setFaculty(departmentDTO.getFaculty());
			departmentRepository.save(department);
			return 1;
		} catch (Exception e) {
			log.error("Unable To Implement: "+e.getMessage());
		}
		return 0;
	}

}
