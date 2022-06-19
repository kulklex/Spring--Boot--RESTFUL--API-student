package com.studentservice.app.service;

import java.util.List;


import com.studentservice.app.dtos.SubjectDTO;
import com.studentservice.app.entity.Subject;

public interface SubjectService {
	Subject addSubject(SubjectDTO subjectDTO);
	Subject updateSubject(Subject subject);
	Subject getSubjects(int id);
	List<Subject>getAllSubjects();
	int deleteSubject(int id);
	Subject enrollStudentToSubject(int subjectId, int studentId);
}
