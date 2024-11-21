package com.example.pdsbackend.service;

import com.example.pdsbackend.DTO.PatientDTO;
import com.example.pdsbackend.model.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    Optional<Patient> getPatientByPersonalId(String personalId);

    Patient createPatient(PatientDTO patientDTO);

    Patient editPatient(Long id, PatientDTO patientDTO);

    void deletePatient(Long id);

    Optional<Patient> getPatientById(Long id);

    List<Patient> listPatients();
}
