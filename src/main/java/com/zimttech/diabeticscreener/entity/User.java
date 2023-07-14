package com.zimttech.diabeticscreener.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity(name = "hbp_user")
public class User extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hbp_user_gen")
    @SequenceGenerator(name = "hbp_user_gen", sequenceName = "hbp_user_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Builder.Default // auto-populate with random UUID
    @Column(name = "uid", nullable = false, unique = true)
    private UUID uid = UUID.randomUUID();
    private String firstName;
    private String middleName;
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    private String primaryPhoneNumber;
    private String secondaryPhoneNumber;
    private String resetToken;
    private Instant resetTokenExpiry;
    private Boolean changePasswordRequest;
    private Instant lastLogin;
    private String status;
    private String username;
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();




}



