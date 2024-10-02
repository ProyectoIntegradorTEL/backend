package com.example.pdsbackend.controller;

import com.example.pdsbackend.DTO.EvaluationDTO;
import com.example.pdsbackend.model.Evaluation;
import com.example.pdsbackend.service.IEvaluationService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Controller
@RequestMapping("/evaluation")
public class EvaluationController {

    private final IEvaluationService evaluationService;

    @Autowired
    public EvaluationController(IEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        Evaluation createdEvaluation = evaluationService.createEvaluation(evaluationDTO);
        return new ResponseEntity<>(createdEvaluation, HttpStatus.CREATED);
    }

    @PostMapping("/sensor")
    public ResponseEntity<Evaluation> createEvaluationFromSensor(@RequestBody String readings) {
        System.out.println("Creating evaluation from sensor. Received: " + readings);
        Evaluation createdEvaluation = evaluationService.createEvaluationFromSensor(readings);
        return new ResponseEntity<>(createdEvaluation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluation> editEvaluation(@PathVariable Long id, @RequestBody EvaluationDTO evaluationDTO) {
        try {
            Evaluation updatedEvaluation = evaluationService.editEvaluation(id, evaluationDTO);
            return new ResponseEntity<>(updatedEvaluation, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> searchEvaluationById(@PathVariable Long id) {
        return evaluationService.searchEvaluationById(id)
                .map(evaluation -> new ResponseEntity<>(evaluation, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Evaluation>> listEvaluations() {
        List<Evaluation> evaluations = evaluationService.listEvaluations();
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }
}
