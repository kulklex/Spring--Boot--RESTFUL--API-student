package com.studentservice.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentservice.app.entity.StudentRelationship;

@Repository
public interface StudentRelationshipRepository extends JpaRepository<StudentRelationship, Integer>{

	StudentRelationship findByStudentID(int studentID);

	StudentRelationship findByStudentIDAndDepartmentID(int studentID, int departmentID);

	List<StudentRelationship> findAllByStudentID(int studentID);

	List<StudentRelationship> findAllByDepartmentID(int departmentID);

}
