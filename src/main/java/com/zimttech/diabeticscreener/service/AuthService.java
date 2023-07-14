package com.zimttech.diabeticscreener.service;

import com.zimttech.diabeticscreener.dto.*;
import com.zimttech.diabeticscreener.entity.User;
import jakarta.servlet.http.HttpServletRequest;


public interface AuthService {

    ResetPasswordResponse resetPassword(String resetToken, ResetPasswordRequest resetPasswordRequest);

    ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest);

    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest);

    UserDto register(User user);

    JwtResponse authenticate(JwtRequest jwtRequestModel) throws Exception;

    UserDto getProfile();

    JwtResponse refreshToken(HttpServletRequest request);

    Boolean verifyToken(VerifyJwtTokenRequest verifyJwtTokenRequest);
}
