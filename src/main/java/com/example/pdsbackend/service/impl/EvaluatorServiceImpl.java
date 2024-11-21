package com.example.pdsbackend.service.impl;

import com.example.pdsbackend.DTO.EvaluatorDTO;
import com.example.pdsbackend.model.Evaluator;
import com.example.pdsbackend.repository.IEvaluatorRepository;
import com.example.pdsbackend.service.IEvaluatorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluatorServiceImpl implements IEvaluatorService {

    private final IEvaluatorRepository evaluatorRepository;

    @Autowired
    public EvaluatorServiceImpl(IEvaluatorRepository evaluatorRepository) {
        this.evaluatorRepository = evaluatorRepository;
    }

    @Override
    public Optional<Evaluator> getEvaluatorByPersonalId(String personalId) {
        return this.evaluatorRepository.findEvaluatorByPersonalId(personalId);
    }

    @Override
    public List<Evaluator> getAllEvaluators() {
        return evaluatorRepository.findAll();
    }

    @Override
    public Optional<Evaluator> getEvaluatorById(Long id) {
        return evaluatorRepository.findById(id);
    }

    @Override
    public Evaluator saveEvaluator(EvaluatorDTO evaluatorDTO) {
        Evaluator evaluator = new Evaluator();
        mapDTOToEntity(evaluatorDTO, evaluator);
        return evaluatorRepository.save(evaluator);
    }

    @Override
    public Evaluator updateEvaluator(Long id, EvaluatorDTO evaluatorDTO) {
        Optional<Evaluator> existingEvaluatorOpt = evaluatorRepository.findById(id);
        if (existingEvaluatorOpt.isPresent()) {
            Evaluator evaluator = existingEvaluatorOpt.get();
            mapDTOToEntity(evaluatorDTO, evaluator);
            return evaluatorRepository.save(evaluator);
        } else {
            throw new EntityNotFoundException("Evaluator with ID " + id + " not found.");
        }
    }

    @Override
    public void deleteEvaluatorById(Long id) {
        evaluatorRepository.deleteById(id);
    }

    private void mapDTOToEntity(EvaluatorDTO dto, Evaluator evaluator) {
        evaluator.setPersonalId(dto.getPersonalId());
        evaluator.setFirstName(dto.getFirstName());
        evaluator.setLastName(dto.getLastName());
        evaluator.setEmail(dto.getEmail());
    }
}
