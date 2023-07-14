package com.zimttech.diabeticscreener.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Patient extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_gen")
    @SequenceGenerator(name = "patient_gen", sequenceName = "patient_seq")
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private int age;
    private String gender;
    private String contactInformation;
    private String insuranceDetails;
    private String medicalHistory;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<PatientVisit> visits;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Vital> vitals;

}
