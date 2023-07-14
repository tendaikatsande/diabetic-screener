package com.zimttech.diabeticscreener.repository;

import com.zimttech.diabeticscreener.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientRepository extends JpaRepository<Patient, Long>, PagingAndSortingRepository<Patient,Long> {
}