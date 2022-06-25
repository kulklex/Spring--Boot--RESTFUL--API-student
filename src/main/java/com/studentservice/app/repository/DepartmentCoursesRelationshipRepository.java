package com.studentservice.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentservice.app.entity.DepartmentCoursesRelationship;

@Repository
public interface DepartmentCoursesRelationshipRepository extends JpaRepository<DepartmentCoursesRelationship, Integer>{

	List<DepartmentCoursesRelationship> findByDeptID(int deptID);

}
