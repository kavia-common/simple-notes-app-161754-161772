package com.example.notesbackend.controller;

import com.example.notesbackend.dto.NoteDtos;
import com.example.notesbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * REST API for managing notes.
 */
@RestController
@RequestMapping(path = "/api/notes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Notes", description = "CRUD operations for notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    // PUBLIC_INTERFACE
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Create a new note",
            description = "Creates a note with the provided title and content.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created",
                            content = @Content(schema = @Schema(implementation = NoteDtos.Response.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error")
            }
    )
    public ResponseEntity<NoteDtos.Response> create(@Valid @RequestBody NoteDtos.CreateRequest request) {
        NoteDtos.Response created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(
            summary = "List notes",
            description = "Returns all notes.",
            responses = @ApiResponse(responseCode = "200", description = "OK")
    )
    public List<NoteDtos.Response> list() {
        return service.list();
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(
            summary = "Get a note",
            description = "Returns a single note by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public NoteDtos.Response get(@PathVariable Long id) {
        return service.get(id);
    }

    // PUBLIC_INTERFACE
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Update a note",
            description = "Updates the title and content of an existing note.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "400", description = "Validation error")
            }
    )
    public NoteDtos.Response update(@PathVariable Long id, @Valid @RequestBody NoteDtos.UpdateRequest request) {
        return service.update(id, request);
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a note",
            description = "Deletes a note by ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Global exception handling for not found and validation issues.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoSuchElementException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "Validation Failed");
        body.put("message", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
