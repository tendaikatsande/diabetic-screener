package com.zimttech.diabeticscreener.controller;

import com.zimttech.diabeticscreener.entity.PatientVisit;
import com.zimttech.diabeticscreener.service.PatientVisitService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/patient-visits/")
@Tag(name = "Patient Visits")
@CrossOrigin()
public class PatientVisitController {

    @Autowired
    PatientVisitService patientVisitService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PatientVisit patientVisit) {
        patientVisit = patientVisitService.create(patientVisit);
        return ResponseEntity.created(URI.create("patient-visits/" + patientVisit.getId())).body(patientVisit);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        patientVisitService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PatientVisit patientVisit) {
        return ResponseEntity.status(201).body(patientVisitService.update(id, patientVisit));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(patientVisitService.getOne(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(patientVisitService.getAll(pageable));
    }
}
