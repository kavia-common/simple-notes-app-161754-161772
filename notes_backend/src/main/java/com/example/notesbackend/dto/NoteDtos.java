package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

/**
 * DTO classes for Note API.
 */
public class NoteDtos {

    /**
     * Create request payload for a note.
     */
    public static class CreateRequest {
        @Schema(description = "Note title", example = "Shopping List", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Title is required")
        public String title;

        @Schema(description = "Note content", example = "Milk, Bread, Eggs", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Content is required")
        public String content;
    }

    /**
     * Update request payload for a note.
     */
    public static class UpdateRequest {
        @Schema(description = "Note title", example = "Updated title")
        @NotBlank(message = "Title is required")
        public String title;

        @Schema(description = "Note content", example = "Updated content")
        @NotBlank(message = "Content is required")
        public String content;
    }

    /**
     * Response payload for a note.
     */
    public static class Response {
        @Schema(description = "Unique identifier", example = "1")
        public Long id;

        @Schema(description = "Note title", example = "Shopping List")
        public String title;

        @Schema(description = "Note content", example = "Milk, Bread, Eggs")
        public String content;

        @Schema(description = "Creation timestamp (UTC)", example = "2024-10-10T12:34:56Z")
        public Instant createdAt;

        @Schema(description = "Last update timestamp (UTC)", example = "2024-10-10T13:34:56Z")
        public Instant updatedAt;
    }
}
