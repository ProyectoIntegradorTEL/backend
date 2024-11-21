package com.example.pdsbackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluatorDTO {
    private String personalId;
    private String firstName; // evaluation type ID
    private String lastName; // patient ID
    private String email; // evaluator ID
}
