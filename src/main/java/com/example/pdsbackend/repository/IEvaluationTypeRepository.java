package com.example.pdsbackend.repository;

import com.example.pdsbackend.model.Evaluation;
import com.example.pdsbackend.model.EvaluationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEvaluationTypeRepository extends JpaRepository<EvaluationType, Long> {
}
