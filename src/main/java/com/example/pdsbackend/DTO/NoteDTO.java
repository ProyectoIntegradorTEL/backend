package com.example.pdsbackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {
    private String note;
    private Long evaluationId; // evaluation type ID
    private Long patientId; // patient ID
    private Long evaluatorId; // evaluator ID
}
