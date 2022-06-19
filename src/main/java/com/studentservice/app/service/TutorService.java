package com.studentservice.app.service;

import java.util.List;

import com.studentservice.app.dtos.TutorDTO;
import com.studentservice.app.entity.Tutor;

public interface TutorService {

	Tutor addTutor(TutorDTO tutorDTO);
	Tutor updateTutor(Tutor tutor);
	Tutor getTutor(int id);
	List<Tutor> getAllTutors();
	int deleteTutor(int id);
}
