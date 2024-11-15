package com.example.pdsbackend.repository;

import com.example.pdsbackend.model.Evaluator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEvaluatorRepository extends JpaRepository<Evaluator, Long> {
    public Optional<Evaluator> findEvaluatorByPersonalId(String personalId);
}
