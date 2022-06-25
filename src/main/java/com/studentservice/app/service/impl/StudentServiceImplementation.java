package com.studentservice.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentservice.app.dtos.ChangeDTO;
import com.studentservice.app.dtos.StuDTO;
import com.studentservice.app.dtos.StudentDTO;
import com.studentservice.app.dtos.StudentRecord;
import com.studentservice.app.entity.Courses;
import com.studentservice.app.entity.Department;
import com.studentservice.app.entity.Student;
import com.studentservice.app.entity.StudentRelationship;
import com.studentservice.app.repository.CoursesRepository;
import com.studentservice.app.repository.DepartmentRepository;
import com.studentservice.app.repository.StudentRelationshipRepository;
import com.studentservice.app.repository.StudentRepository;
import com.studentservice.app.services.StudentService;
import com.studentservice.app.utils.DateUtils;

@Service
public class StudentServiceImplementation implements StudentService {
	
	private Logger log = LoggerFactory.getLogger(StudentServiceImplementation.class);
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	DepartmentRepository deptRepository;
	@Autowired
	CoursesRepository courseRepository;
	@Autowired
	StudentRelationshipRepository studentRelationshipRepository;
	@Autowired
	DateUtils dateUtils;

	@Override
	public int addStudent(StudentDTO studentDTO) {
		log.info("Adding Student");
		
		if (studentDTO.getFirstName() == null || studentDTO.getFirstName().isEmpty() || studentDTO.getLastName() == null || studentDTO.getLastName().isEmpty()
				|| studentDTO.getEmail() == null || studentDTO.getEmail().isEmpty() || studentDTO.getPhoneNumber() == null || studentDTO.getPhoneNumber().isEmpty()) {
			return 3;
		}
		
		try {
			
			//check if student already exists
			Student isStudent = studentRepository.findByFirstNameAndLastName(studentDTO.getFirstName(), studentDTO.getLastName());
			if (isStudent != null) {
				return 2;
			}
			
			Student student = new Student();
			student.setAge(studentDTO.getAge());
			student.setCreatedDate(dateUtils.getCurrentTimestamp());
			student.setEmail(studentDTO.getEmail());
			student.setFirstName(studentDTO.getFirstName());
			student.setGender(studentDTO.getGender());
			student.setLastName(studentDTO.getLastName());
			student.setLevel(studentDTO.getLevel());
			student.setMatricNumber(studentDTO.getMatricNumber());
			student.setPhoneNumber(studentDTO.getPhoneNumber());
			student.setDepartmentID(""+studentDTO.getDepartmentID());
			studentRepository.save(student);
			StudentRelationship rel = new StudentRelationship();
			rel.setStatus(true);
			rel.setDepartmentID(studentDTO.getDepartmentID());
			rel.setStudentID(student.getId());
			studentRelationshipRepository.save(rel);
			return 1;
		}catch(Exception e) {
			log.error("Unable To Implement: "+e.getMessage());
			return 0;
		}
		
	}

	@Override
	public int updateStudent(StuDTO studentDTO) {
		log.info("Updating Student Record");
		try {
			//Todo: Check If Student ID is Valid
			Optional<Student> isStudent = studentRepository.findById(studentDTO.getStudentID());
			if (isStudent.isPresent()) {
				StudentRelationship studentRelationship = studentRelationshipRepository.findByStudentIDAndDepartmentID(studentDTO.getStudentID(), Integer.parseInt(isStudent.get().getDepartmentID()));
				if (studentRelationship != null) {
					studentRelationship.setDepartmentID(Integer.parseInt(studentDTO.getDepartment()));
					studentRelationshipRepository.save(studentRelationship);
				}
				isStudent.get().setMatricNumber(studentDTO.getMatricNumber());
				isStudent.get().setDepartmentID(studentDTO.getDepartment());
				studentRepository.save(isStudent.get());
				return 1;
			}
			return 2;
			
			
		}catch(Exception e) {
			log.error("Unable To Implement: "+e.getMessage());
			return 0;
		}
	}

	@Override
	public int addStudentCourses(List<String> courseIDs, int studentID, int deptID) {
		log.info("Adding Student Courses In Department");
		
			for (String id : courseIDs) {
				StudentRelationship student = new StudentRelationship();
				student.setCourseID(Integer.parseInt(id));
				student.setDepartmentID(deptID);
				student.setStudentID(studentID);
				studentRelationshipRepository.save(student);
			}
			return 1;
		
	}

	@Override
	public StudentRecord getStudent(int studentID) {
		log.info("Getting Student Record");
		
		List<String> names = new ArrayList<>();
		StudentRecord record = new StudentRecord();
		Optional<Student> isStudent = studentRepository.findById(studentID);
		if (isStudent.isPresent()) {
			Optional<Department> dept = deptRepository.findById(Integer.parseInt(isStudent.get().getDepartmentID()));
			record.setDepartment(dept.get().getName() != null ? dept.get().getName() : "");
			record.setFaculty(dept.get().getFaculty() != null ? dept.get().getFaculty() : "");
			record.setStudent(isStudent.get());
			List<StudentRelationship> studentRelationship = studentRelationshipRepository.findAllByStudentID(studentID);
			for (StudentRelationship rel : studentRelationship) {
				Optional<Courses> courses = courseRepository.findById(rel.getCourseID());
				if (!courses.isPresent()) {
					continue;
				}
				names.add(courses.get().getCourseName());
				record.setCourseNames(names);
			}
			
			return record;
		}
		return null;
	}

	@Override
	public List<Student> getStudentsByDepartmentID(int departmentID) {
		log.info("Fetching All Students In Department");
		
		List<Student> students = new ArrayList<>();
		List<StudentRelationship> studentRels = studentRelationshipRepository.findAllByDepartmentID(departmentID);
		
		for (StudentRelationship rel : studentRels) {
			Optional<Student> student = studentRepository.findById(rel.getStudentID());
			if(student.isPresent()) {
				students.add(student.get());
			}
			continue;
		}
		return students;
	}

	@Override
	public List<Courses> getCoursesByStudentID(int studentID) {
		log.info("Getting Courses Offered by student");
		List<Courses> courses = new ArrayList<>();
		List<StudentRelationship> crses = studentRelationshipRepository.findAllByStudentID(studentID);
		if (courses.size() > 0) {
			for (StudentRelationship rels : crses) {
				Optional<Courses> course = courseRepository.findById(rels.getCourseID());
				if (course.isPresent()) {
					courses.add(course.get());
				}
				continue;
			}
			return courses;
		}
		return new ArrayList<Courses>();
	}

	@Override
	public int changeStudentStatus(int studentID,int deptID, boolean status) {
		log.info("Changing Student School Status");
		
		Optional<Student> student = studentRepository.findById(studentID);
		if (student.isPresent()) {
			StudentRelationship studentRel = studentRelationshipRepository.findByStudentIDAndDepartmentID(studentID, deptID);
			studentRel.setStatus(status);
			studentRelationshipRepository.save(studentRel);
			return 1;
		}
		return 0;	
	}

	@Override
	public int paySchoolFees(int studentID,int deptID) {
		log.info("Pay School Fees");
		Optional<Student> student = studentRepository.findById(studentID);
		if (student.isPresent()) {
			StudentRelationship studentRel = studentRelationshipRepository.findByStudentID(student.get().getId());
			studentRel.setSchoolFeesPaid(true);;
			studentRelationshipRepository.save(studentRel);
			return 1;
		}
		return 0;
	}

	@Override
	public int changeDepartment(ChangeDTO changeDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
