package com.example.pdsbackend.service.impl;

import com.example.pdsbackend.DTO.PatientDTO;
import com.example.pdsbackend.model.Patient;
import com.example.pdsbackend.repository.IPatientRepository;
import com.example.pdsbackend.service.IPatientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements IPatientService {

    private final IPatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Patient> getPatientByPersonalId(String personalId) {
        return this.patientRepository.getPatientByPersonalId(personalId);
    }

    @Override
    public Patient createPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        mapDTOToEntity(patientDTO, patient);
        return patientRepository.save(patient);
    }

    @Override
    public Patient editPatient(Long id, PatientDTO patientDTO) {
        Optional<Patient> existingPatientOpt = patientRepository.findById(id);
        if (existingPatientOpt.isPresent()) {
            Patient patient = existingPatientOpt.get();
            mapDTOToEntity(patientDTO, patient);
            return patientRepository.save(patient);
        } else {
            throw new EntityNotFoundException("Patient with ID " + id + " not found.");
        }
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public List<Patient> listPatients() {
        return patientRepository.findAll();
    }

    private void mapDTOToEntity(PatientDTO dto, Patient patient) {
        patient.setPersonalId(dto.getPersonalId());
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setGender(dto.getGender());
        patient.setBirthDate(dto.getBirthDate());
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhone());
    }
}
