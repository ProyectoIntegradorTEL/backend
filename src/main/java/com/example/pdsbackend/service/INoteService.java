package com.example.pdsbackend.service;

import com.example.pdsbackend.DTO.NoteDTO;
import com.example.pdsbackend.model.Note;

import java.util.List;
import java.util.Optional;

public interface INoteService {
    List<Note> getAllNotes();
    Optional<Note> getNoteById(Long id);
    Note saveNote(NoteDTO note);
    Note updateNote(Long id, NoteDTO noteDetails);
    void deleteNoteById(Long id);
}
