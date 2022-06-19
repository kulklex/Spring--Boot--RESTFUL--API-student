package com.studentservice.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentservice.app.dtos.SubjectDTO;
import com.studentservice.app.entity.Student;
import com.studentservice.app.entity.Subject;
import com.studentservice.app.exceptions.EmptyTextFieldsException;
import com.studentservice.app.exceptions.ResourcesNotFoundException;
import com.studentservice.app.repository.StudentRepository;
import com.studentservice.app.repository.SubjectRepository;


@Service
public class SubjectServiceImplementation implements SubjectService{

	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	
	@Override
	public Subject addSubject(SubjectDTO subjectDTO) {
		System.out.println("Adding New Subject");
		if(subjectDTO.getCivic() == null || subjectDTO.getCivic().isEmpty() || subjectDTO.getEconomics() == null || subjectDTO.getEconomics().isEmpty() ||
				subjectDTO.getEnglish() == null || subjectDTO.getEnglish().isEmpty() || subjectDTO.getMathematics() == null || subjectDTO.getMathematics().isEmpty() ||
				subjectDTO.getScience() == null || subjectDTO.getScience().isEmpty() || subjectDTO.getTech() == null || subjectDTO.getTech().isEmpty()) {
			throw new EmptyTextFieldsException("Fill Up Empty Subject Textfields");
		}
		Subject subject = new Subject();
		subject.setCivic(subjectDTO.getCivic());
		subject.setEconomics(subjectDTO.getEconomics());
		subject.setEnglish(subjectDTO.getEnglish());
		subject.setMathematics(subjectDTO.getMathematics());
		subject.setScience(subjectDTO.getScience());
		subject.setTech(subjectDTO.getTech());
		
		subjectRepository.save(subject);
		return subject;
	}

	@Override
	public Subject updateSubject(Subject subjectDTO) {
		System.out.println("Updating New Subject");
		if(subjectDTO.getCivic() == null || subjectDTO.getCivic().isEmpty() || subjectDTO.getEconomics() == null || subjectDTO.getEconomics().isEmpty() ||
				subjectDTO.getEnglish() == null || subjectDTO.getEnglish().isEmpty() || subjectDTO.getMathematics() == null || subjectDTO.getMathematics().isEmpty() ||
				subjectDTO.getScience() == null || subjectDTO.getScience().isEmpty() || subjectDTO.getTech() == null || subjectDTO.getTech().isEmpty()) {
			throw new EmptyTextFieldsException("Fill Up Empty Subject Textfields");
		}
		
		Optional<Subject> subject = subjectRepository.findById(subjectDTO.getId());
		if(subject.isPresent()) {
			subject.get().setCivic(subjectDTO.getCivic());
			subject.get().setEconomics(subjectDTO.getEconomics());
			subject.get().setEnglish(subjectDTO.getEnglish());
			subject.get().setMathematics(subjectDTO.getMathematics());
			subject.get().setScience(subjectDTO.getScience());
			subject.get().setTech(subjectDTO.getTech());
			
			subjectRepository.save(subjectDTO);
		}
		throw new ResourcesNotFoundException(subjectDTO);
	}

	@Override
	public Subject getSubjects(int id) {
		return subjectRepository.findById(id).get();
	}

	@Override
	public List<Subject> getAllSubjects() {
		return subjectRepository.findAll();
	}

	@Override
	public int deleteSubject(int id) {
		System.out.println("Deleting Existing Subject");
		Optional<Subject> subject = subjectRepository.findById(id);
		if(subject.isPresent()) {
			subjectRepository.delete(subject.get());
			return 1;
		}
		return 0;
	}

	@Override
	public Subject enrollStudentToSubject(int subjectId, int studentId) {
		System.out.println("Assigning a Subject to a Student");
		Optional<Subject> subject = subjectRepository.findById(subjectId);
		if(subject.isPresent()) {
			Optional<Student> student = studentRepository.findById(studentId);
			if(student.isPresent()) {
				subject.get().enrollStudent(student.get());
				return subjectRepository.save(subject.get());
			}
			throw new ResourcesNotFoundException(student);
		}
		throw new ResourcesNotFoundException(subject);
	}

}
