package com.example.pdsbackend.service.impl;

import com.example.pdsbackend.DTO.EvaluationDTO;
import com.example.pdsbackend.model.Evaluation;
import com.example.pdsbackend.model.EvaluationType;
import com.example.pdsbackend.model.Patient;
import com.example.pdsbackend.repository.IEvaluationRepository;
import com.example.pdsbackend.repository.IEvaluationTypeRepository;
import com.example.pdsbackend.repository.IPatientRepository;
import com.example.pdsbackend.service.IEvaluationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationServiceImpl implements IEvaluationService {

    private final IEvaluationRepository evaluationRepository;
    private final IEvaluationTypeRepository evaluationTypeRepository;
    private final IPatientRepository patientRepository;

    @Autowired
    public EvaluationServiceImpl(IEvaluationRepository evaluationRepository,
                                 IEvaluationTypeRepository evaluationTypeRepository,
                                 IPatientRepository patientRepository) {
        this.evaluationRepository = evaluationRepository;
        this.evaluationTypeRepository = evaluationTypeRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public Evaluation createEvaluation(EvaluationDTO evaluationDTO) {
        Evaluation evaluation = new Evaluation();
        evaluation.setDate(evaluationDTO.getDate());
        evaluation.setDuration(evaluationDTO.getDuration());
        evaluation.setJsonData(evaluationDTO.getJsonData());
        evaluation.setNote(evaluationDTO.getNote());

        // Obtener y establecer las entidades relacionadas
        EvaluationType evaluationType = evaluationTypeRepository.findById(evaluationDTO.getEvaluationTypeId())
                .orElseThrow(() -> new EntityNotFoundException("EvaluationType not found."));
        evaluation.setEvaluationType(evaluationType);

        Patient patient = patientRepository.findById(evaluationDTO.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found."));
        evaluation.setPatient(patient);

        return evaluationRepository.save(evaluation);
    }

    @Override
    public Evaluation editEvaluation(Long id, EvaluationDTO evaluationDTO) {
        Optional<Evaluation> existingEvaluationOpt = evaluationRepository.findById(id);
        if (existingEvaluationOpt.isPresent()) {
            Evaluation evaluation = existingEvaluationOpt.get();
            evaluation.setDate(evaluationDTO.getDate());
            evaluation.setDuration(evaluationDTO.getDuration());
            evaluation.setJsonData(evaluationDTO.getJsonData());
            evaluation.setNote(evaluationDTO.getNote());

            // Actualizar las entidades relacionadas
            EvaluationType evaluationType = evaluationTypeRepository.findById(evaluationDTO.getEvaluationTypeId())
                    .orElseThrow(() -> new EntityNotFoundException("EvaluationType not found."));
            evaluation.setEvaluationType(evaluationType);

            Patient patient = patientRepository.findById(evaluationDTO.getPatientId())
                    .orElseThrow(() -> new EntityNotFoundException("Patient not found."));
            evaluation.setPatient(patient);

            return evaluationRepository.save(evaluation);
        } else {
            throw new EntityNotFoundException("Evaluation with ID " + id + " not found.");
        }
    }

    @Override
    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);
    }

    @Override
    public Optional<Evaluation> searchEvaluationById(Long id) {
        return evaluationRepository.findById(id);
    }

    @Override
    public List<Evaluation> listEvaluations() {
        return evaluationRepository.findAll();
    }
}