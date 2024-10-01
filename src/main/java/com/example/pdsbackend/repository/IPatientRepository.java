package com.example.pdsbackend.repository;

import com.example.pdsbackend.model.Patient;
import com.example.pdsbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
}
