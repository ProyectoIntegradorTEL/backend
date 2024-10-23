package com.example.pdsbackend.repository;

import com.example.pdsbackend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
    public Optional<Patient> getPatientByPersonalId(String personalId);
}
