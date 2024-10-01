package com.example.pdsbackend.service;

import com.example.pdsbackend.DTO.EvaluationTypeDTO;
import com.example.pdsbackend.model.EvaluationType;

import java.util.List;
import java.util.Optional;

public interface IEvaluationTypeService {
    public EvaluationType createEvaluationType(EvaluationTypeDTO evaluationTypeDTO);
    public void deleteEvaluationType(Long id);
    public Optional<EvaluationType> searchEvaluationTypeById(Long id);
    public List<EvaluationType> listEvaluationTypes();
    public EvaluationType editEvaluationType(Long id, EvaluationTypeDTO evaluationTypeDTO);
}
