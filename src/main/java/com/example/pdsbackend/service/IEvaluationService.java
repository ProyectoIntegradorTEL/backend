package com.example.pdsbackend.service;

import com.example.pdsbackend.DTO.EvaluationDTO;
import com.example.pdsbackend.model.Evaluation;

import java.util.List;
import java.util.Optional;

public interface IEvaluationService {
    public Evaluation createEvaluation(EvaluationDTO evaluationDTO);
    public void deleteEvaluation(Long id);
    public Optional<Evaluation> searchEvaluationById(Long id);
    public List<Evaluation> listEvaluations();
    public Evaluation editEvaluation(Long id, EvaluationDTO evaluationDTO);
}
