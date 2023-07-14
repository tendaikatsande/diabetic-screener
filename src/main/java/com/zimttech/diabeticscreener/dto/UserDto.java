package com.zimttech.diabeticscreener.dto;


import com.zimttech.diabeticscreener.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private UUID uid;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String primaryPhoneNumber;
    private String secondaryPhoneNumber;
    private String resetToken;
    private Instant resetTokenExpiry;
    private Boolean changePasswordRequest;
    private Instant lastLogin;
    private String status;
    private String username;
    private Collection<Role> roles;
}
