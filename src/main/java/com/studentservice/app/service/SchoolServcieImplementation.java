package com.studentservice.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentservice.app.dtos.SchoolDTO;
import com.studentservice.app.entity.School;
import com.studentservice.app.exceptions.EmptyTextFieldsException;
import com.studentservice.app.exceptions.ResourcesNotFoundException;
import com.studentservice.app.repository.SchoolRepository;


@Service
public class SchoolServcieImplementation implements SchoolService {
	
	
	
	@Autowired
	SchoolRepository schoolRepository;

	@Override
	public School addSchool(SchoolDTO schooldto) {
		System.out.println("Adding New School");
		if(schooldto.getName() == null || schooldto.getName().isEmpty() || schooldto.getCity() == null || schooldto.getCity().isEmpty()) {
			throw new EmptyTextFieldsException("Fill Up Empty School Textfields");
		}
		
		School school = new School();
		school.setName(schooldto.getName());
		school.setCity(schooldto.getCity());
		schoolRepository.save(school);
		
		return school;
	}

	@Override
	public School findOneSchool(int id) {
		System.out.println("Fetching Particular Schools Bt ID");
		return schoolRepository.findById(id).get();
	}

	@Override
	public List<School> getAllAvaliableSchools() {
		
		return schoolRepository.findAll();
	}

	@Override
	public School updateSchool(School schooldto) {
		System.out.println("Updating Existing School");
		if(schooldto.getName() == null || schooldto.getName().isEmpty() || schooldto.getCity() == null || schooldto.getCity().isEmpty()) {
			throw new EmptyTextFieldsException("Fill Up Empty School Textfields");
		}
		Optional<School> school = schoolRepository.findById(schooldto.getId());
		if (school.isPresent()) {
			school.get().setCity(schooldto.getCity());
			school.get().setName(schooldto.getName());
			
			schoolRepository.save(schooldto);
			return schooldto;
		}
		throw new ResourcesNotFoundException(schooldto);
	}

	@Override
	public int deleteSchool(int id) {
		System.out.println("Deleting Existing School");
		Optional<School> school = schoolRepository.findById(id);
		if(school.isPresent()) {
			schoolRepository.delete(school.get());
			return 1;
		}
		return 0;
	}

}
