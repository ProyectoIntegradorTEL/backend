package com.example.pdsbackend.DTO;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationDTO implements Serializable {
    private LocalDateTime date;
    private double duration;
    private JsonNode jsonData;
    private String note;
    private Long evaluationTypeId; // evaluation type ID
    private Long patientId; // patient ID
}
