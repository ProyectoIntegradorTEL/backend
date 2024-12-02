package com.example.pdsbackend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pdsbackend.DTO.EvaluationDTO;
import com.example.pdsbackend.model.Evaluation;
import com.example.pdsbackend.service.IEvaluationService;

import jakarta.persistence.EntityNotFoundException;



@RestController
@Controller
@RequestMapping("/evaluation")
public class EvaluationController {

    private final IEvaluationService evaluationService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public EvaluationController(IEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }


    @Autowired
    private SimpUserRegistry simpUserRegistry;

    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody EvaluationDTO evaluationDTO) {


        try{

            Evaluation createdEvaluation = evaluationService.createEvaluation(evaluationDTO);
            return new ResponseEntity<>(createdEvaluation, HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

     
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Test", HttpStatus.OK);
    }



    @PostMapping("/preview")
    public  ResponseEntity<String>  setEvaluationPreview(@RequestBody String readings) {
        
        try {

            System.out.println(readings);
            simpMessagingTemplate.convertAndSend("/dataTopic", readings);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    

    @PostMapping("/sensor")
    public ResponseEntity<Evaluation> createEvaluationFromSensor(@RequestBody String readings) {
        System.out.println("Creating evaluation from sensor. Received: " + readings);
        Evaluation createdEvaluation = evaluationService.createEvaluationFromSensor(readings);
        return new ResponseEntity<>(createdEvaluation, HttpStatus.CREATED);
    }

    @GetMapping("/csv")
    public ResponseEntity<byte[]> getEvaluationsAsCsv() {
        try {
            byte[] csvData = evaluationService.generateCsv();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=evaluations.csv");
            headers.add("Content-Type", "text/csv");

            return new ResponseEntity<>(csvData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @GetMapping(params = "patientId")
    public ResponseEntity<List<Evaluation>> searchEvaluationByPatientId(@RequestParam Long patientId) {
        System.out.println("Searching evaluation by patient id: " + patientId);
        List<Evaluation> evaluations = evaluationService.searchEvaluationByPatientId(patientId);
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Evaluation>> listEvaluations() {
        List<Evaluation> evaluations = evaluationService.listEvaluations();
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }
}
