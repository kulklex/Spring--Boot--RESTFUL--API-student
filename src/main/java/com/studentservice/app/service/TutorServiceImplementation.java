package com.studentservice.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentservice.app.dtos.TutorDTO;
import com.studentservice.app.entity.Tutor;
import com.studentservice.app.exceptions.EmptyTextFieldsException;
import com.studentservice.app.exceptions.ResourcesNotFoundException;
import com.studentservice.app.repository.TutorRepository;


@Service
public class TutorServiceImplementation implements TutorService {
	
	@Autowired
	TutorRepository tutorRepository;

	@Override
	public Tutor addTutor(TutorDTO tutorDTO) {
		System.out.println("Creating New Tutor");
		if(tutorDTO.getAge() == null || tutorDTO.getAge().isEmpty() || tutorDTO.getFirstName() == null || tutorDTO.getFirstName().isEmpty() || tutorDTO.getLastName() == null ||
				tutorDTO.getLastName().isEmpty() || tutorDTO.getEmail() == null || tutorDTO.getEmail().isEmpty() || tutorDTO.getGender() == null || tutorDTO.getGender().isEmpty() ||
				tutorDTO.getPhoneNumber() == null || tutorDTO.getPhoneNumber().isEmpty()) {
			throw new EmptyTextFieldsException("Fill Up Empty Tutor Textfields");
		}
		Tutor tutor = new Tutor();
		tutor.setAge(tutorDTO.getAge());
		tutor.setEmail(tutorDTO.getEmail());
		tutor.setFirstName(tutorDTO.getFirstName());
		tutor.setLastName(tutorDTO.getLastName());
		tutor.setGender(tutorDTO.getGender());
		tutor.setPhoneNumber(tutorDTO.getPhoneNumber());
		
		tutorRepository.save(tutor);
		return tutor;
	}

	@Override
	public Tutor updateTutor(Tutor tutorDTO) {
		System.out.println("Updating the Tutor");
		if(tutorDTO.getAge() == null || tutorDTO.getAge().isEmpty() || tutorDTO.getFirstName() == null || tutorDTO.getFirstName().isEmpty() || tutorDTO.getLastName() == null ||
				tutorDTO.getLastName().isEmpty() || tutorDTO.getEmail() == null || tutorDTO.getEmail().isEmpty() || tutorDTO.getGender() == null || tutorDTO.getGender().isEmpty() ||
				tutorDTO.getPhoneNumber() == null || tutorDTO.getPhoneNumber().isEmpty()) {
			throw new EmptyTextFieldsException("Fill Up Empty Tutor Textfields");
		}
		
		Optional<Tutor> tutor = tutorRepository.findById(tutorDTO.getId());
		if(tutor.isPresent()) {
			tutor.get().setAge(tutorDTO.getAge());
			tutor.get().setEmail(tutorDTO.getEmail());
			tutor.get().setFirstName(tutorDTO.getFirstName());
			tutor.get().setGender(tutorDTO.getGender());
			tutor.get().setLastName(tutorDTO.getLastName());
			tutor.get().setPhoneNumber(tutorDTO.getPhoneNumber());
			tutorRepository.save(tutorDTO);
			return tutorDTO;
		}
		throw new ResourcesNotFoundException(tutorDTO);
	}

	@Override
	public Tutor getTutor(int id) {
	return tutorRepository.findById(id).get();
	}

	@Override
	public List<Tutor> getAllTutors() {
		return tutorRepository.findAll();
	}

	@Override
	public int deleteTutor(int id) {
		Optional<Tutor> tutor = tutorRepository.findById(id);
		if(tutor.isPresent()) {
			tutorRepository.delete(tutor.get());
			return 1;
		}
		return 0;
	}
}
