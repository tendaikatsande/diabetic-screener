package com.zimttech.diabeticscreener.service.impl;

import com.zimttech.diabeticscreener.entity.Patient;
import com.zimttech.diabeticscreener.repository.PatientRepository;
import com.zimttech.diabeticscreener.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;
    @Override
    public Page<Patient> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Patient> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public Patient create(Patient patient) {
        return repository.save(patient);
    }

    @Override
    public Patient update(Long id, Patient patient) {
        return create(patient);
    }



    @Override
    public void delete(Long id) {
repository.deleteById(id);
    }
}
