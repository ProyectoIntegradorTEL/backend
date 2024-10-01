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

    // many to one, we need that 'ONE' instance
    @ManyToOne
    private EvaluationType evaluationType;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Note note;

    public Evaluation(Long id, LocalDateTime date, double duration, JsonNode jsonData) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.jsonData = jsonData;
    }

    public Evaluation() {}

    public Evaluation(Evaluation evaluation){
        this(evaluation.getId(), evaluation.getDate(), evaluation.getDuration(), evaluation.getJsonData());
    }
}