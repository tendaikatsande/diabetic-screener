package com.zimttech.diabeticscreener.dto;

import lombok.*;

import java.time.Instant;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RegisterAccountRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String primaryPhoneNumber;
    private String secondaryPhoneNumber;
    private String otp;
    private String resetToken;
    private String resetTokenExpiry;
    private Boolean changePasswordRequest;
    private Instant lastLogin;
    private String status;
    private String username;
    private String password;
    private Boolean deActivated;
    private Boolean locked;
    private Collection<Long> roles;
    private Long workSpace;
    private Collection<Long> teams;
}
