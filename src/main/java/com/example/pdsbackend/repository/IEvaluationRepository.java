package com.example.pdsbackend.repository;

import com.example.pdsbackend.model.Evaluation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByPatientId(Long id);
    @Query("SELECT e FROM Evaluation e LEFT JOIN FETCH e.notes")
    List<Evaluation> findAllWithNotes();
}
