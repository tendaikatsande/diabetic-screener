package com.zimttech.diabeticscreener.controller;

import com.zimttech.diabeticscreener.entity.User;
import com.zimttech.diabeticscreener.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;

@RestController
@RequestMapping("/api/users/")
@Tag(name="Users")
@CrossOrigin()
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        user = userService.create(user);
        return ResponseEntity.created(URI.create("users/" + user.getId())).body(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.status(201).body(userService.update(id, user));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(userService.getAllUsers(pageable));
    }
}
