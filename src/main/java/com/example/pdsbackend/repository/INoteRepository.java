package com.example.pdsbackend.repository;

import com.example.pdsbackend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INoteRepository extends JpaRepository<Note, Long> {
}
