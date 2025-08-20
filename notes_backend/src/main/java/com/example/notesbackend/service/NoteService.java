package com.example.notesbackend.service;

import com.example.notesbackend.dto.NoteDtos;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Service for managing notes.
 */
@Service
@Transactional
public class NoteService {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    // PUBLIC_INTERFACE
    public NoteDtos.Response create(NoteDtos.CreateRequest request) {
        Note note = new Note(request.title, request.content);
        Note saved = repository.save(note);
        return toResponse(saved);
    }

    // PUBLIC_INTERFACE
    @Transactional(readOnly = true)
    public List<NoteDtos.Response> list() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    // PUBLIC_INTERFACE
    @Transactional(readOnly = true)
    public NoteDtos.Response get(Long id) {
        Note note = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Note not found: " + id));
        return toResponse(note);
    }

    // PUBLIC_INTERFACE
    public NoteDtos.Response update(Long id, NoteDtos.UpdateRequest request) {
        Note note = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Note not found: " + id));
        note.setTitle(request.title);
        note.setContent(request.content);
        Note saved = repository.save(note);
        return toResponse(saved);
    }

    // PUBLIC_INTERFACE
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Note not found: " + id);
        }
        repository.deleteById(id);
    }

    private NoteDtos.Response toResponse(Note note) {
        NoteDtos.Response resp = new NoteDtos.Response();
        resp.id = note.getId();
        resp.title = note.getTitle();
        resp.content = note.getContent();
        resp.createdAt = note.getCreatedAt();
        resp.updatedAt = note.getUpdatedAt();
        return resp;
    }
}
