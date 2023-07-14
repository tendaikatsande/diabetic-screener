package com.zimttech.diabeticscreener.controller;

import com.zimttech.diabeticscreener.dto.*;
import com.zimttech.diabeticscreener.entity.User;
import com.zimttech.diabeticscreener.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth")
@CrossOrigin()
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest jwtRequestModel) throws Exception {
        return ResponseEntity.ok().body(authService.authenticate(jwtRequestModel));
    }

    @GetMapping(value = "/refresh-token")
    public ResponseEntity<JwtResponse> refreshToken(HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @PostMapping(value = "/verify-token")
    public ResponseEntity<Boolean> verifyToken(@RequestBody VerifyJwtTokenRequest verifyJwtTokenRequest) throws Exception {
        return ResponseEntity.ok(authService.verifyToken(verifyJwtTokenRequest));
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        return ResponseEntity.ok(authService.forgotPassword(forgotPasswordRequest));
    }

    @PostMapping("/change-password")
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseEntity.ok(authService.changePassword(changePasswordRequest));
    }


    @GetMapping("/reset-password/{resetToken}")
    public ResponseEntity<ResetPasswordResponse> forgotPassword(@PathVariable String resetToken, @RequestBody ResetPasswordRequest resetPasswordRequest) {
        return ResponseEntity.ok(authService.resetPassword(resetToken, resetPasswordRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody User user) {
        UserDto userDto = authService.register(user);
        return ResponseEntity.created(URI.create("users/" + userDto.getId())).body(userDto);
    }


    @GetMapping("/profile")
    public ResponseEntity<UserDto> profile() {
        return ResponseEntity.ok(authService.getProfile());
    }


}
