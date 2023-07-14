package com.zimttech.diabeticscreener.service.impl;

import com.zimttech.diabeticscreener.dto.*;
import com.zimttech.diabeticscreener.entity.User;
import com.zimttech.diabeticscreener.mapper.UserMapper;
import com.zimttech.diabeticscreener.service.AuthService;
import com.zimttech.diabeticscreener.service.UserService;
import com.zimttech.diabeticscreener.util.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;


@Service
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;




    @Override
    public ResetPasswordResponse resetPassword(String resetToken, ResetPasswordRequest resetPasswordRequest) {
        if (!resetPasswordRequest.getConfirmPassword().equals(resetPasswordRequest.getPassword()))
            throw new BadCredentialsException("Password does not match confirm password");
       User user = userService.findByResetToken(resetToken).orElseThrow(() -> new BadCredentialsException("Password reset link not valid"));
        boolean isExpired = ChronoUnit.MINUTES.between(Instant.now(), user.getResetTokenExpiry()) > 15;
        if (isExpired) {
            throw new BadCredentialsException("Password reset Expired");
        }

        user.setChangePasswordRequest(false);
        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
        userService.create(user);

        return new ResetPasswordResponse("Password reset successful");
    }


    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
           User user = userService.getUserByUsername(((UserDetails) authentication.getPrincipal()).getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            if (passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                userService.create(user);
                return new ChangePasswordResponse("Password changed successfully");
            }
            throw new BadCredentialsException("Current password is incorrect");
        }
        throw new UsernameNotFoundException("User not authenticated");
    }


    @Override
    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        User user = userService.getUserByUsername(forgotPasswordRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found with email " + forgotPasswordRequest.getEmail()));
        user.setChangePasswordRequest(true);
        user.setResetToken(UUID.randomUUID().toString());
        user.setResetTokenExpiry(Instant.now().plus(15, ChronoUnit.MINUTES));
        userService.create(user);
        return new ForgotPasswordResponse("message with link has been sent to your email. Please check your email and follow contained instructions");
    }

    @Override
    public UserDto register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userService.create(user);
        return UserMapper.INSTANCE.userToUserDto(userService.create(user));
    }

    @Override
    public JwtResponse authenticate(JwtRequest jwtRequestModel) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestModel.getUsername(), jwtRequestModel.getPassword()));
        final UserDetails userDetails = userService.loadUserByUsername(jwtRequestModel.getUsername());
        return new JwtResponse(tokenManager.generateJwtToken(userDetails), tokenManager.generateRefreshToken(userDetails));
    }

    @Override
    public UserDto getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserDetails) {

            return UserMapper.INSTANCE.userToUserDto(userService.getUserByUsername(((UserDetails) authentication.getPrincipal()).getUsername()).orElseThrow(() -> new UsernameNotFoundException("User name not found")));
        }
        return null;
    }

    @Override
    public JwtResponse refreshToken(HttpServletRequest request) {

        String refreshToken = tokenManager.extractRefreshTokenFromRequest(request);
        if (tokenManager.validateRefreshToken(refreshToken)) {
            UserDetails userDetails = userService.loadUserByUsername(tokenManager.getUsernameFromToken(refreshToken));
            String newToken = tokenManager.generateJwtToken(userDetails);
            return new JwtResponse(newToken, refreshToken);
        }
        return null;
    }

    @Override
    public Boolean verifyToken(VerifyJwtTokenRequest verifyJwtTokenRequest) {
        return tokenManager.verifyJwtToken(verifyJwtTokenRequest.getToken());
    }


}
