package com.zimttech.diabeticscreener.entity;

import com.zimttech.diabeticscreener.util.BloodPressureCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vital extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vitals_gen")
    @SequenceGenerator(name = "vitals_gen", sequenceName = "vitals_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "visit_id")
    private PatientVisit visit;
    private BloodPressureCategory bloodPressure;
    private double bodyTemperature;
    private int heartRate;
    private double weight;
    private double height;
}
