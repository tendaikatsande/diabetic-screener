package com.zimttech.diabeticscreener.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
public class Role extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Role_GEN")
    @SequenceGenerator(name = "Role_GEN", sequenceName = "Role_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;


}
