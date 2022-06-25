package com.studentservice.app.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentservice.app.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

	Department findByName(String name);
	
}
