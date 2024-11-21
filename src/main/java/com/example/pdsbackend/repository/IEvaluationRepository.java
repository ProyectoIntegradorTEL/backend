package com.example.pdsbackend.repository;

import com.example.pdsbackend.model.Evaluation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByPatientId(Long id);
}
