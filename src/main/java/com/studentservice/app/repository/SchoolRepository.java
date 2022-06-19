package com.studentservice.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentservice.app.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {

}
