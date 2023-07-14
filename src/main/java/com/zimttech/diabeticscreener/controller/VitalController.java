package com.zimttech.diabeticscreener.controller;

import com.zimttech.diabeticscreener.entity.Vital;
import com.zimttech.diabeticscreener.service.VitalsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/vitals")
@Tag(name = "Vitals")
@CrossOrigin()
public class VitalController {

    @Autowired
    VitalsService vitalsService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Vital vital) {
        vital = vitalsService.create(vital);
        return ResponseEntity.created(URI.create("vitals/" + vital.getId())).body(vital);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        vitalsService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Vital vital) {
        return ResponseEntity.status(201).body(vitalsService.update(id, vital));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(vitalsService.getOne(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(vitalsService.getAll(pageable));
    }
}
