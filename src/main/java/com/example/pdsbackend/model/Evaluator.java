package com.example.pdsbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Evaluator")
@Table(name = "EVALUATOR")
@Data
public class Evaluator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String personalId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    public Evaluator(Long id, String personalId, String firstName, String lastName, String email) {
        this.id = id;
        this.personalId = personalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Evaluator() {}

    public Evaluator(Evaluator evaluator){
        this(evaluator.getId(), evaluator.getPersonalId(), evaluator.getFirstName(), evaluator.getLastName(), evaluator.getEmail());
    }
}
