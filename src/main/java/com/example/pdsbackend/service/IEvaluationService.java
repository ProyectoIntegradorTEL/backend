package com.example.pdsbackend.service;

import com.example.pdsbackend.DTO.EvaluationDTO;
import com.example.pdsbackend.model.Evaluation;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public interface IEvaluationService {
    public Evaluation createEvaluation(EvaluationDTO evaluationDTO);
    public void deleteEvaluation(Long id);
    public Optional<Evaluation> searchEvaluationById(Long id);
    public List<Evaluation> listEvaluations();
    public Evaluation editEvaluation(Long id, EvaluationDTO evaluationDTO);
    public Evaluation createEvaluationFromSensor(String readings);
    public byte[] generateCsv() throws IOException;
}
