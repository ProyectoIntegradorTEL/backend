package com.example.pdsbackend.controller;

import com.example.pdsbackend.DTO.EvaluationTypeDTO;
import com.example.pdsbackend.model.EvaluationType;
import com.example.pdsbackend.service.IEvaluationTypeService;
import jakarta.annotation.security.PermitAll;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// DESCOMENTAR import org.springframework.security.access.prepost.PreAuthorize;
// DESCOMENTAR import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@CrossOrigin
@RequestMapping("/evaluation-types")
public class EvaluationTypeController {
    private final IEvaluationTypeService evaluationTypeService;

    @Autowired
    public EvaluationTypeController(IEvaluationTypeService evaluationTypeService) {
        this.evaluationTypeService = evaluationTypeService;
    }

    @PostMapping
    public ResponseEntity<EvaluationType> createEvaluationType(@RequestBody EvaluationTypeDTO evaluationTypeDTO) {
        System.out.println("entra a crear EVALUATION TYPE");
        EvaluationType createdEvaluationType = evaluationTypeService.createEvaluationType(evaluationTypeDTO);
        return new ResponseEntity<>(createdEvaluationType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationType> editEvaluationType(@PathVariable Long id, @RequestBody EvaluationTypeDTO evaluationTypeDTO) {
        try {
            EvaluationType updatedEvaluationType = evaluationTypeService.editEvaluationType(id, evaluationTypeDTO);
            return new ResponseEntity<>(updatedEvaluationType, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluationType(@PathVariable Long id) {
        evaluationTypeService.deleteEvaluationType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationType> searchEvaluationTypeById(@PathVariable Long id) {
        return evaluationTypeService.searchEvaluationTypeById(id)
                .map(evaluationType -> new ResponseEntity<>(evaluationType, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<EvaluationType>> listEvaluationTypes() {
        List<EvaluationType> evaluationTypes = evaluationTypeService.listEvaluationTypes();
        return new ResponseEntity<>(evaluationTypes, HttpStatus.OK);
    }
}
