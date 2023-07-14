package com.zimttech.diabeticscreener.controller;

import com.zimttech.diabeticscreener.entity.Patient;
import com.zimttech.diabeticscreener.service.PatientService;
import com.zimttech.diabeticscreener.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/patients/")
@Tag(name = "Patient")
@CrossOrigin()
public class PatientController {
    @Autowired
    PatientService patientService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Patient patient) {
        patient = patientService.create(patient);
        return ResponseEntity.created(URI.create("patients/" + patient.getId())).body(patient);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Patient patient) {
        return ResponseEntity.status(201).body(patientService.update(id, patient));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(patientService.getOne(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(patientService.getAll(pageable));
    }
}
