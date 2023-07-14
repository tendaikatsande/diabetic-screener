package com.zimttech.diabeticscreener.controller;

import com.zimttech.diabeticscreener.entity.Role;
import com.zimttech.diabeticscreener.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/roles")
@Tag(name="Roles")
@CrossOrigin()
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody Role role) {
        role = roleService.create(role);
        return ResponseEntity.created(URI.create("roles/"+role.getId())).body(role) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody  Role role) {
        return ResponseEntity.status(201).body(roleService.update(id,role));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(roleService.getOne(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(roleService.getAll(pageable));
    }
}
