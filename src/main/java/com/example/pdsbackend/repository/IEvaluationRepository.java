package com.example.pdsbackend.repository;

import com.example.pdsbackend.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEvaluationRepository extends JpaRepository<Evaluation, Long> {
}
