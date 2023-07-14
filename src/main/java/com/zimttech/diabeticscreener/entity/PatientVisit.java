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
public class PatientVisit extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_visit_gen")
    @SequenceGenerator(name = "patient_visit_gen", sequenceName = "patient_visit_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private String visitDate;
    private String visitReason;
    private int visitDuration;

    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL)
    private List<Vital> vitals;
}
