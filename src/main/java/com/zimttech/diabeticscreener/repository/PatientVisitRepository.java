package com.zimttech.diabeticscreener.repository;

import com.zimttech.diabeticscreener.entity.PatientVisit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientVisitRepository extends JpaRepository<PatientVisit, Long> {
}