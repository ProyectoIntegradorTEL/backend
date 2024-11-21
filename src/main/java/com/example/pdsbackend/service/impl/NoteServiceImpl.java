package com.example.pdsbackend.service.impl;

import com.example.pdsbackend.DTO.NoteDTO;
import com.example.pdsbackend.model.Evaluation;
import com.example.pdsbackend.model.Evaluator;
import com.example.pdsbackend.model.Note;
import com.example.pdsbackend.model.Patient;
import com.example.pdsbackend.repository.IEvaluationRepository;
import com.example.pdsbackend.repository.IEvaluatorRepository;
import com.example.pdsbackend.repository.INoteRepository;
import com.example.pdsbackend.repository.IPatientRepository;
import com.example.pdsbackend.service.INoteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements INoteService {

    private final INoteRepository noteRepository;
    private final IEvaluatorRepository evaluatorRepository;
    private final IPatientRepository patientRepository;
    private final IEvaluationRepository evaluationRepository;

    @Autowired
    public NoteServiceImpl(
            INoteRepository noteRepository,
            IEvaluatorRepository evaluatorRepository,
            IPatientRepository patientRepository,
            IEvaluationRepository evaluationRepository) {
        this.noteRepository = noteRepository;
        this.evaluatorRepository = evaluatorRepository;
        this.patientRepository = patientRepository;
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public Note saveNote(NoteDTO noteDTO) {
        Note note = new Note();
        mapDTOToEntity(noteDTO, note);
        return noteRepository.save(note);
    }

    @Override
    public Note updateNote(Long id, NoteDTO noteDetails) {
        Optional<Note> existingNoteOpt = noteRepository.findById(id);
        if (existingNoteOpt.isPresent()) {
            Note note = existingNoteOpt.get();
            mapDTOToEntity(noteDetails, note);
            return noteRepository.save(note);
        } else {
            throw new EntityNotFoundException("Note with ID " + id + " not found.");
        }
    }

    @Override
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }

    private void mapDTOToEntity(NoteDTO dto, Note note) {
        note.setComment(dto.getNote());

        // Relación con Evaluator (obligatoria)
        Evaluator evaluator = evaluatorRepository.findEvaluatorByPersonalId(dto.getEvaluatorId().toString())
                .orElseThrow(() -> new EntityNotFoundException("Evaluator with ID " + dto.getEvaluatorId() + " not found."));
        note.setEvaluator(evaluator);

        // Relación con Patient (opcional)
        if (dto.getPatientId() != null) {
            Patient patient = patientRepository.getPatientByPersonalId(dto.getPatientId().toString())
                    .orElseThrow(() -> new EntityNotFoundException("Patient with ID " + dto.getPatientId() + " not found."));
            note.setPatient(patient);
        } else {
            note.setPatient(null);
        }

        // Relación con Evaluation (opcional)
        if (dto.getEvaluationId() != null) {
            Evaluation evaluation = evaluationRepository.findById(dto.getEvaluationId())
                    .orElseThrow(() -> new EntityNotFoundException("Evaluation with ID " + dto.getEvaluationId() + " not found."));
            note.setEvaluation(evaluation);
        } else {
            note.setEvaluation(null);
        }
    }
}
