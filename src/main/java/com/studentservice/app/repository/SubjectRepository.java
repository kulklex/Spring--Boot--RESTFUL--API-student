package com.studentservice.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentservice.app.entity.Subject;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
