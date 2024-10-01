package com.example.pdsbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "EvaluationType")
@Table(name = "EVALUATION_TYPE")
@Data
public class EvaluationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public EvaluationType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public EvaluationType() {}

    public EvaluationType(EvaluationType evaluationType){
        this(evaluationType.getId(), evaluationType.getName());
    }
}
