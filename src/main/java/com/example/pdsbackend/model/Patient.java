package com.example.pdsbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "Patient")
@Table(name = "PATIENT")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String personalId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    public Patient(Long id, String personalId, String firstName, String lastName, String gender, LocalDate birthDate,
                   String email, String phone) {
        this.id = id;
        this.personalId = personalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
    }

    public Patient() {}

    public Patient(Patient patient){
        this(patient.getId(), patient.getPersonalId(), patient.getFirstName(), patient.getLastName(),
                patient.getGender(), patient.getBirthDate(), patient.getEmail(), patient.getPhone());
    }
}
