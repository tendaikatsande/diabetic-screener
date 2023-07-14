package com.zimttech.diabeticscreener.service.impl;

import com.zimttech.diabeticscreener.entity.PatientVisit;
import com.zimttech.diabeticscreener.repository.PatientVisitRepository;
import com.zimttech.diabeticscreener.service.PatientVisitService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class PatientVisitServiceImpl implements PatientVisitService {

    private final PatientVisitRepository repository;
    @Override
    public Page<PatientVisit> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<PatientVisit> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public PatientVisit create(PatientVisit patientVisit) {
        return repository.save(patientVisit);
    }

    @Override
    public PatientVisit update(Long id, PatientVisit patientVisit) {
        return create(patientVisit);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
