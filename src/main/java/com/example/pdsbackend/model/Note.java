package com.example.pdsbackend.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity(name = "Note")
@Table(name = "NOTE")
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    public Note(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public Note() {}

    public Note(Note note){
        this(note.getId(), note.getComment());
    }
}
