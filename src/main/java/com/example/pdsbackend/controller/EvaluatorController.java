package com.example.pdsbackend.controller;

import com.example.pdsbackend.DTO.EvaluatorDTO;
import com.example.pdsbackend.model.Evaluator;
import com.example.pdsbackend.service.IEvaluatorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/evaluator")
public class EvaluatorController {

    private final IEvaluatorService evaluatorService;

    @Autowired
    public EvaluatorController(IEvaluatorService evaluatorService) {
        this.evaluatorService = evaluatorService;
    }

    @PostMapping
    public ResponseEntity<Evaluator> createEvaluator(@RequestBody EvaluatorDTO evaluatorDTO) {
        Evaluator createdEvaluator = evaluatorService.saveEvaluator(evaluatorDTO);
        return new ResponseEntity<>(createdEvaluator, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluator> updateEvaluator(@PathVariable Long id, @RequestBody EvaluatorDTO evaluatorDTO) {
        try {
            Evaluator updatedEvaluator = evaluatorService.updateEvaluator(id, evaluatorDTO);
            return new ResponseEntity<>(updatedEvaluator, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluator(@PathVariable Long id) {
        evaluatorService.deleteEvaluatorById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluator> getEvaluatorById(@PathVariable Long id) {
        return evaluatorService.getEvaluatorByPersonalId(id.toString())
                .map(evaluator -> new ResponseEntity<>(evaluator, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/personalId/{personalId}")
    public ResponseEntity<Evaluator> getEvaluatorByPersonalId(@PathVariable String personalId) {
        return evaluatorService.getEvaluatorByPersonalId(personalId)
                .map(evaluator -> new ResponseEntity<>(evaluator, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Evaluator>> getAllEvaluators() {
        List<Evaluator> evaluators = evaluatorService.getAllEvaluators();
        return new ResponseEntity<>(evaluators, HttpStatus.OK);
    }
}
