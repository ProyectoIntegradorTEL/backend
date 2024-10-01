package com.example.pdsbackend.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "Evaluation")
@Table(name = "EVALUATION")
@Data
public class Evaluation {
    /* @SequenceGenerator(
            name="evaluation_sequence",
            sequenceName = "evaluation_sequence",
            allocationSize = 1
    )*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private double duration;

    @Column(columnDefinition = "jsonb")  // jsonb in Postgres
    @Convert(converter = JsonNodeConverter.class)  // custom converter data type
    private JsonNode jsonData;

    // Note entity isn't necessary
    private String note;

    // many to one, we need that 'ONE' instance
    @ManyToOne
    private EvaluationType evaluationType;

    @ManyToOne
    private Patient patient;

    public Evaluation(Long id, LocalDateTime date, double duration, JsonNode jsonData, String note) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.jsonData = jsonData;
        this.note = note;
    }

    public Evaluation() {}

    public Evaluation(Evaluation evaluation){
        this(evaluation.getId(), evaluation.getDate(), evaluation.getDuration(), evaluation.getJsonData(),
                evaluation.getNote());
    }
}