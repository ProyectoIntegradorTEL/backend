package com.example.pdsbackend.service;

import com.example.pdsbackend.DTO.EvaluatorDTO;
import com.example.pdsbackend.model.Evaluator;

import java.util.List;
import java.util.Optional;

public interface IEvaluatorService {
    Optional<Evaluator> getEvaluatorByPersonalId(String personalId);
    List<Evaluator> getAllEvaluators();
    Optional<Evaluator> getEvaluatorById(Long id);
    Evaluator saveEvaluator(EvaluatorDTO note);
    Evaluator updateEvaluator(Long id, EvaluatorDTO evaluator);
    void deleteEvaluatorById(Long id);
}
