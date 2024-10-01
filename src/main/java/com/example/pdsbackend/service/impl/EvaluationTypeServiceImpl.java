package com.example.pdsbackend.service.impl;

import com.example.pdsbackend.DTO.EvaluationTypeDTO;
import com.example.pdsbackend.model.EvaluationType;
import com.example.pdsbackend.repository.IEvaluationTypeRepository;
import com.example.pdsbackend.service.IEvaluationTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationTypeServiceImpl implements IEvaluationTypeService {

    private final IEvaluationTypeRepository evaluationTypeRepository;

    @Autowired
    public EvaluationTypeServiceImpl(IEvaluationTypeRepository evaluationTypeRepository) {
        this.evaluationTypeRepository = evaluationTypeRepository;
    }

    @Override
    public EvaluationType createEvaluationType(EvaluationTypeDTO evaluationTypeDTO) {
        EvaluationType evaluationType = new EvaluationType();
        evaluationType.setName(evaluationTypeDTO.getName());
        return evaluationTypeRepository.save(evaluationType);
    }

    @Override
    public EvaluationType editEvaluationType(Long id, EvaluationTypeDTO evaluationTypeDTO) {
        Optional<EvaluationType> existingEvaluationTypeOpt = evaluationTypeRepository.findById(id);
        if (existingEvaluationTypeOpt.isPresent()) {
            EvaluationType evaluationType = existingEvaluationTypeOpt.get();
            evaluationType.setName(evaluationTypeDTO.getName());
            return evaluationTypeRepository.save(evaluationType);
        } else {
            throw new EntityNotFoundException("EvaluationType with ID " + id + " not found.");
        }
    }

    @Override
    public void deleteEvaluationType(Long id) {
        evaluationTypeRepository.deleteById(id);
    }

    @Override
    public Optional<EvaluationType> searchEvaluationTypeById(Long id) {
        return evaluationTypeRepository.findById(id);
    }

    @Override
    public List<EvaluationType> listEvaluationTypes() {
        return evaluationTypeRepository.findAll();
    }
}
