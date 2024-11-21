package com.example.pdsbackend.controller;

import com.example.pdsbackend.DTO.PatientDTO;
import com.example.pdsbackend.model.Patient;
import com.example.pdsbackend.service.IPatientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/patient")
public class PatientController {

    private final IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDTO patientDTO) {
        Patient createdPatient = patientService.createPatient(patientDTO);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> editPatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        try {
            Patient updatedPatient = patientService.editPatient(id, patientDTO);
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientByPersonalId(@PathVariable String id) {
        return patientService.getPatientByPersonalId(id)
                .map(patient -> new ResponseEntity<>(patient, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Patient>> listPatients() {
        List<Patient> patients = patientService.listPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
